# reststyle项目

#### 项目介绍:
SpringBoot整合security示例实现权限注解+JWT登录认证(数据库中测试号的密码进行了加密,密码皆为123456)

相关文章请移步到:https://github.com/1415749952/reststyle

SpringBoot版本:2.1.6

SpringSecurity版本: 5.1.5

MyBatis-Plus版本: 3.1.0

#### swagger接口：
文档地址：http://localhost:8089/swagger-ui/index.html

#### 项目接口说明
###### 1登陆前获取验证码接口
localhost:8089/login/captchaImage     
###### 2登录接口
localhost:8089/login/userLogin
###### 登录接口请求参数
{
"username":"admin",
"password":"123456",
"code":"16",
"uuid":"38cd0736755c4bf9847e03863835ffaf"
}

