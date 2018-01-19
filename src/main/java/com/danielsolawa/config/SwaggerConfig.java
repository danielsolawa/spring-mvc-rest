package com.danielsolawa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * Created by Daniel Solawa on 2018-01-19.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(metaData());
    }

    private ApiInfo metaData(){
        Contact contact = new Contact("Daniel Solawa", "http://danielsolawa.com", "danielsolawa@gmail.com");

        return new ApiInfo(
                "Spring Mvc Rest",
                "Sample application",
                "1.0",
                "Terms of Service:",
                contact,
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }
}
