package com.mt.inventory;

import java.util.Collection;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
public class InventoryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementApplication.class, args);
	}

	@Bean
	public Docket getDocketApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.mt.inventory")).build().apiInfo(apiDetail());
	}

	private ApiInfo apiDetail() {
		return new ApiInfo("Inventory Management", "Customer can view and order stock", "1.0", "Free t use",
				new springfox.documentation.service.Contact("Aman Mishra", "Google.com", "mishraaman469@gmail.com"),
				"Api License", "Google.com", Collections.EMPTY_LIST);
	}

}
