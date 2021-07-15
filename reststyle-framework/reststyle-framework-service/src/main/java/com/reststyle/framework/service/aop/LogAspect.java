package com.reststyle.framework.service.aop;


import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.reststyle.framework.common.oper_log.OperLog;
import com.reststyle.framework.common.oper_log.OperStatus;
import com.reststyle.framework.common.security.SecurityUtils;
import com.reststyle.framework.common.security.entity.SecurityUser;
import com.reststyle.framework.common.utils.DateUtils;
import com.reststyle.framework.common.utils.ServletUtils;
import com.reststyle.framework.common.utils.id.IdWorker;
import com.reststyle.framework.common.utils.ip.IpUtils;
import com.reststyle.framework.common.utils.json.JacksonUtils;
import com.reststyle.framework.common.utils.spring.SpringUtils;
import com.reststyle.framework.domain.table.SysOperLog;
import com.reststyle.framework.service.manager.AsyncManager;
import com.reststyle.framework.service.manager.factory.AsyncFactory;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * Description:这个是最主要的类,可以使用自定义注解或针对包名实现AOP增强。
 * 1)这里实现了对自定义注解的环绕增强切点，对使用了自定义注解的方法进行AOP切面处理；
 * 2)对方法运行时间进行监控；
 * 3)对方法名，参数名，参数值，对日志描述的优化处理；
 * 在方法上增加@Aspect 注解声明切面,使用@Pointcut 注解定义切点，标记方法。
 * 使用切点增强的时机注解:@Before,@Around,@AfterReturning,@AfterThrowing,@After
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-27
 * @Time: 17:27
 */
@Slf4j
@Aspect
@Component
public class LogAspect
{

    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.reststyle.framework.common.oper_log.OperLog)")
    public void operLogPoinCut()
    {
    }

    /**
     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
     *
     * @param joinPoint 切入点
     * @param keys      返回结果
     */
    @AfterReturning(value = "operLogPoinCut()", returning = "keys")
    public void doAfterReturning(JoinPoint joinPoint, Object keys)
    {
        handleLog(joinPoint, null, keys);
    }

    /**
     * 异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行
     *
     * @param joinPoint 切入点
     * @param e         异常信息
     */
    @AfterThrowing(pointcut = "operLogPoinCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e)
    {
        handleLog(joinPoint, e, null);
    }


    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult)
    {
        try
        {
            // 获得注解
            OperLog controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null)
            {
                return;
            }
            // 获取当前的用户
            SecurityUser loginUser = SecurityUtils.getLoginUser();
            SysOperLog operLog = new SysOperLog();
            operLog.setOperId(IdWorker.getId());
            operLog.setState(OperStatus.SUCCESS.getState());
            // 请求的IP地址
            operLog.setOperIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
            //请求的URL地址
            operLog.setRequestUrl(ServletUtils.getRequest().getRequestURI());
            // 设置请求方式
            operLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            final UserAgent userAgent = UserAgentUtil.parse(ServletUtils.getRequest().getHeader("User-Agent"));
            operLog.setOperClient(userAgent.getBrowser().getName());
            //operLog.setOperAddress(loginUser.getLoginLocation());
            //设置请求时间
            operLog.setOperTime(DateUtils.getNowDate());
            // 是否保存请求参数和值
            if (controllerLog.isSaveRequestData())
            {
                // 设置请求参数和值
                operLog.setRequestParam(getRequestParam(ServletUtils.getRequest(), joinPoint));
            }
            // 返回参数
            operLog.setResposeResult(JacksonUtils.object2Json(jsonResult));
            // 设置业务类型
            operLog.setBusinessType(controllerLog.businessType().getKey());
            // 设置接口描述
            operLog.setMethodDetail(controllerLog.methodDetail());
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethod(className + "." + methodName + "()");
            // 设置操作的对象
            operLog.setOperUnit(controllerLog.operUnit().getValue());
            if (null != loginUser)
            {
                operLog.setOperName(loginUser.getUsername());
            }
            if (null != e)
            {
                operLog.setState(OperStatus.FAIL.getState());
                operLog.setErrorMsg(StrUtil.sub(e.toString(), 0, 2000));
            }

            // 保存数据库
            AsyncManager.me().execute(AsyncFactory.recordOper(operLog));
        }
        catch (Exception exp)
        {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }



    /**
     * 是否存在注解，如果存在就获取
     */
    private OperLog getAnnotationLog(JoinPoint joinPoint)
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
            return method.getAnnotation(OperLog.class);
        }
        return null;
    }

    /**
     * 获取请求的参数，放到log中
     */
    public String getRequestParam(HttpServletRequest request, JoinPoint joinPoint)
    {
        Map<String, String[]> paramMap = request.getParameterMap();
        String requestParam = "";
        //如果请求数据不在body里面
        if (paramMap.size() != 0)
        {
            Map<String, String> rtnMap = new HashMap<String, String>();
            for (String key : paramMap.keySet())
            {
                rtnMap.put(key, paramMap.get(key)[0]);
            }
            requestParam = JacksonUtils.object2Json(rtnMap);
        }
        else
        {
            Object[] args = joinPoint.getArgs();
            if (args.length > 0 && args[0] instanceof MultipartFile)
            {
                MultipartFile file = (MultipartFile) joinPoint.getArgs()[0];
                HashMap<String, String> data = new HashMap<>();
                data.put("fileName", file.getName());
                return JacksonUtils.object2Json(data);
            }
            requestParam = JacksonUtils.object2Json(joinPoint.getArgs());
        }
        return requestParam;
    }

}
