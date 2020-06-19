package com.payzap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PayzapApp {

	public static void main(String[] args) {

		SpringApplication.run(PayzapApp.class, args);
		
		System.out.println("PayZap Started.....");

	}

}
