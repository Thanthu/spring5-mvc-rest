package com.thanthu.springmvcrest.conig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

// doc available at https://springdoc.org/
@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		Contact contact = new Contact();
		contact.setName("Thanthu Nair");
		contact.url("https://www.linkedin.com/in/thanthu-nair-4871a778/");
		contact.setEmail("thanthu.u.nair@gmail.com");

		return new OpenAPI().info(new Info().title("Spring Boot Rest Examples")
				.description("An example project showcasing Srping Boot Rest project").version("1.0")
				.termsOfService("Terms Of Service: Nooo!").contact(contact)
				.license(new License().name("My License name").url("https://mylicenseurl")));
	}

}
