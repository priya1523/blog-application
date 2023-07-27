package com.feuji.blog.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Priya Patel
 * This class is a configuration class for the swaager documentation
 */
@Configuration
public class SwaggerConfiguration 
{

	private ApiInfo getInfo()
	{
		return new ApiInfo(
				"Blog-Application",
				"This application is about the sharing your thoughts", 
				"1.0", 
				"Tearms & COndition", 
				new Contact("Priya Patel", "priya patel", "piyupatel1523@gmail.com"), 
				"License of Api's", 
				"Api license url",
				Collections.emptyList());
	}
	
	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2)
								.apiInfo(getInfo())
								.select()
								.apis(RequestHandlerSelectors.any())
								.paths(PathSelectors.any())
								.build();
	}
}
