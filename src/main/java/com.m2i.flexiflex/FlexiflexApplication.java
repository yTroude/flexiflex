package com.m2i.flexiflex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EntityScan(basePackages = { "com.m2i.flexiflex.entity"})
public class FlexiflexApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlexiflexApplication.class, args);

    }
}
