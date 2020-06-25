package com.ims.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableCaching
@EnableEurekaClient
@EnableFeignClients
public class BooksInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksInventoryApplication.class, args);
	}

}
