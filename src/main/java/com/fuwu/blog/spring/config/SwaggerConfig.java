package com.fuwu.blog.spring.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fuwu.blog.security.SecurityConstants;

import springfox.documentation.service.Parameter;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
    	
    	ParameterBuilder paramBuilder = new ParameterBuilder();
        List<Parameter> params = new ArrayList<>();
        paramBuilder.name(SecurityConstants.TOKEN_HEADER)
        			.description("身份认证token")
        			.modelRef(new ModelRef("string")).parameterType("header")
        			.required(false)
        			.build(); 
        params.add(paramBuilder.build());  
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.fuwu.blog.controller"))              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiInfo()).globalOperationParameters(params);                                           
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("博客系统后台接口文档")
                .version("1.0")
                .build();
    }
}