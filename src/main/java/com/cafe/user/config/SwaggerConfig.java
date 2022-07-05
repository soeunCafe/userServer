package com.cafe.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cafe.user.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    //RequestHandlerSelectors.basePackage(String packageName)
    // : Swagger를 적용할 클래스의 package 명

    //PathSelectors.any()
    // 해당 package 하위에 있는 모든 url에 적용시킨다.


    // API의 이름은 무엇이며 현재 버전은 어떻게 되는지 해당 API에 대한 정보
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Cafe")
                .description("Cafe api docs")
                .version("1.0")
                .build();
    }
}
