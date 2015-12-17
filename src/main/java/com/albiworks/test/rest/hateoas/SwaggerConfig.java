package com.albiworks.test.rest.hateoas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@Configuration
@EnableSwagger
public class SwaggerConfig {

	@Bean
	public SwaggerSpringMvcPlugin customImplementation(SpringSwaggerConfig springSwaggerConfig) {
		return new SwaggerSpringMvcPlugin(springSwaggerConfig).apiInfo(apiInfo())
				.includePatterns("/accounts/.*");
	}
	
	public ApiInfo apiInfo(){
		ApiInfo api = new ApiInfo("Accounts Management", "Simple Api to manage accounts and balances", 
				"/terms_of_service.txt", "alex@albiworks.com", 
				"Apache License, Version 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
		
		return api;
	}
	
}
