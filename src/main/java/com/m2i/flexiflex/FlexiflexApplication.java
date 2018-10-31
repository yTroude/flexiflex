package com.m2i.flexiflex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlexiflexApplication {

    private static String username = "benoit";
    private static String password = "secret";

	public static void main(String[] args) {
		SpringApplication.run(FlexiflexApplication.class, args);
        System.out.println("Hello world!");
	}
}
