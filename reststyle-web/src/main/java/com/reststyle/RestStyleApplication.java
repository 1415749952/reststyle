package com.reststyle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2021-03-24
 * @Time: 18:26
 */
@EnableOpenApi
@SpringBootApplication
public class RestStyleApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RestStyleApplication.class, args);
    }
}