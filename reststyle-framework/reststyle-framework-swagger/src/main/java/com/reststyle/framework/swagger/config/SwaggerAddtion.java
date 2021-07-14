package com.reststyle.framework.swagger.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:手动添加swagger接口，如登录接口等
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-07-14
 * @Time: 17:22
 */
@Component
public class SwaggerAddtion implements ApiListingScannerPlugin
{

    @Override
    public List<ApiDescription> apply(DocumentationContext documentationContext) {
        return new ArrayList<ApiDescription>(
                Arrays.asList(
                        new ApiDescription(
                                "/oauth/token",  //url
                                "UserToken", //描述
                                Arrays.asList(
                                        new OperationBuilder(
                                                new CachingOperationNameGenerator())
                                                .method(HttpMethod.POST)//http请求类型
                                                .produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE))
                                                .summary("获取token")
                                                .notes("获取token")//方法描述
                                                .tags(Sets.newHashSet("Token"))//归类标签
                                                .parameters(
                                                        Arrays.asList(
                                                                new ParameterBuilder()
                                                                        .description("oauth2鉴权方式，如password")//参数描述
                                                                        .type(new TypeResolver().resolve(String.class))//参数数据类型
                                                                        .name("grant_type")//参数名称
                                                                        .defaultValue("password")//参数默认值
                                                                        .parameterType("query")//参数类型
                                                                        .parameterAccess("access")
                                                                        .required(true)//是否必填
                                                                        .modelRef(new ModelRef("string")) //参数数据类型
                                                                        .build(),
                                                                new ParameterBuilder()
                                                                        .description("用户名")
                                                                        .type(new TypeResolver().resolve(String.class))
                                                                        .name("username")
                                                                        .parameterType("query")
                                                                        .parameterAccess("access")
                                                                        .required(true)
                                                                        .modelRef(new ModelRef("string")) //<5>
                                                                        .build(),
                                                                new ParameterBuilder()
                                                                        .description("密码")
                                                                        .type(new TypeResolver().resolve(String.class))
                                                                        .name("password")
                                                                        .parameterType("query")
                                                                        .parameterAccess("access")
                                                                        .required(true)
                                                                        .modelRef(new ModelRef("string")) //<5>
                                                                        .build()
                                                        ))
                                                .build()),
                                false)));
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return DocumentationType.SWAGGER_2.equals(documentationType);
    }
}