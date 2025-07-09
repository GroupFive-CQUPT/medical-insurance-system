package com.thefive.apiserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * Swagger API文档配置
     */
    @Bean
    public Docket getDocket() {
        // 创建API信息
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("《医疗保险报销系统》后端接口说明")
                .description("医疗保险报销系统RESTful API接口文档")
                .version("v 1.0.0")
                .contact(new Contact("开发团队", "", "developer@example.com"));
        ApiInfo apiInfo = apiInfoBuilder.build();

        // 创建Docket对象
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cqupt.medical.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}