package com.example.Project.configg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig
{
 @Bean
 public OpenAPI productDiscountAPI()
 {
	 return new OpenAPI()
			 .info(new Info()
					 .title("Product discount management api")
					 .version("1.0")
					 .description("API for calculating and applying discounts to products"));
 }
}
