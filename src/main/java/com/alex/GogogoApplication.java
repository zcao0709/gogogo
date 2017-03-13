package com.alex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//@ImportResource("redis.xml") // for xml based configuration
public class GogogoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GogogoApplication.class, args);
	}
}
