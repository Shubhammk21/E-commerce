package com.ECommerce;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxCoonfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo()).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
    }

    private ApiInfo getInfo() {
        return new ApiInfo("Online Shopping Application",
                "This project is developed by Masai School Student \n"+
                        "Name - E-Commerce \n"+
                        "Shubham Singh",
                "1.0", "Term of Services",
                new springfox.documentation.service.Contact("Team main-transport-9463", "https://github.com/Shubhammk21/E-commerce", "abc@gmail.com"),
                "Licence of API",
                "https://github.com/Shubhammk21/E-commerce",
                Collections.emptyList());
    }

}