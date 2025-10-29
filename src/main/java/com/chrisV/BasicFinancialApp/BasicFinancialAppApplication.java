package com.chrisV.BasicFinancialApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasicFinancialAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicFinancialAppApplication.class, args);
        System.out.println("The Basic Financial App is running!");
	}

}
