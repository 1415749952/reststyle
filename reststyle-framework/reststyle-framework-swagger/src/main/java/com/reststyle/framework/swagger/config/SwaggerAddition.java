package com.reststyle.framework.swagger.config;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static java.util.Collections.singletonList;

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
public class SwaggerAddition implements ApiListingScannerPlugin
{

    @Override
    public List<ApiDescription> apply(DocumentationContext context)
    {
        String loginJsonStr = "{\n" +
                "    \"username\":\"admin\",\n" +
                "    \"password\":\"123456\",\n" +
                "    \"code\":\"5\",\n" +
                "    \"uuid\":\"ea34a4554f55418c83011b4cd403fb22\"\n" +
                "}";

        RequestBody requestBody = new RequestBody(
                "登录请求主体",
                new HashSet<>(Arrays.asList(new RepresentationBuilder()
                        .model(modelSpecificationBuilder -> modelSpecificationBuilder.facets(modelFacetsBuilder -> modelFacetsBuilder.examples(Arrays.asList(new ExampleBuilder().mediaType(MediaType.APPLICATION_JSON_VALUE).value(loginJsonStr).build()))).scalarModel(ScalarType.OBJECT).build()).mediaType(MediaType.APPLICATION_JSON_VALUE).build())),
                true,
                Arrays.asList(new StringVendorExtension("name", "value"))
        );

        List<ApiDescription> apiDescriptions = singletonList(new ApiDescription(
                "登录",
                "/login/userLogin",
                "用户登录Api",
                "用户登录API(手动添加API)",
                singletonList(
                        new OperationBuilder(new CachingOperationNameGenerator())
                                .authorizations(new ArrayList<>())
                                .tags(new HashSet()
                                {{
                                    add("登录模块");
                                }})
                                .method(HttpMethod.POST)
                                .notes("username/password登录")
                                .summary("用户登录获取token")
                                .requestBody(requestBody)
                                .responses(singletonList(new ResponseBuilder().code("200").description("ok").representation(MediaType.ALL).apply(r -> r.model(m -> m.scalarModel(ScalarType.OBJECT))).build()))
                                .build()
                ),
                false
        ));

        return apiDescriptions;
    }

    @Override
    public boolean supports(DocumentationType delimiter)
    {
        return DocumentationType.OAS_30.equals(delimiter);
    }
}