package com.tube.clone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket restAPI(TypeResolver resolver) {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.additionalModels(resolver.resolve(DummyDomain.class))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.tube.clone"))
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo apiInfo() {	
		return new ApiInfoBuilder()
				.title("Sample Project API Info")
				.version("0.0.1")
				.description("Sample Project API")
				.build();
				
	}
}
