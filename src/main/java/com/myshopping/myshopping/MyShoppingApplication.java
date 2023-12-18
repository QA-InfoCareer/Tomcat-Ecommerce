package com.myshopping.myshopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
//@EnableConfigServer
public class MyShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyShoppingApplication.class, args);
	}

}
