package com.reststyle.framework.swagger.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-14
 * @Time: 15:56
 */
public class SwaggerConfig implements WebMvcConfigurer
{


    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例，用来控制那些借口暴露给Swagger来展现
     * 本例采用指定扫描的包路径来定义指定要建立API的目录
     *
     * @return
     */
    @Bean
    public Docket createRestApi()
    {
        Set<String> set = new HashSet<>();
        set.add("https");
        set.add("http");
        return new Docket(DocumentationType.OAS_30)
                .pathMapping("/")
                .enable(true)//定义是否开启swagger，false为关闭，可以通过变量控制
                .apiInfo(apiInfo())//将api的元信息设置为包含在json ResourceListing响应中。
                .select()//选择哪些接口作为swagger的doc发布
                //.apis(RequestHandlerSelectors.basePackage("com.best.user"))//将那些包作为接口文档来显示
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .protocols(set)// 支持的通讯协议集合
                .securitySchemes(securitySchemes())// 授权信息设置，必要的header token等认证信息
                .securityContexts(securityContexts());// 授权信息全局应用
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     *
     * @return
     */
    private ApiInfo apiInfo()
    {
        return new ApiInfoBuilder()
                .title("reststyle接口文档")
                .description("reststyle接口文档。")
                .version("1.0")
                .build();
    }

    /**
     * 设置授权信息
     */
    private List<SecurityScheme> securitySchemes()
    {
        ApiKey apiKey = new ApiKey("BASE_TOKEN", "Authorization-Access", io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER.toString());
        return Collections.singletonList(apiKey);
    }

    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts()
    {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(Collections.singletonList(new SecurityReference("BASE_TOKEN", new AuthorizationScope[]{new AuthorizationScope("global", "")})))
                        .build()
        );
    }



}
