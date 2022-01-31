package com.exxeta.expensemanagerms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.exxeta")
public class ExpenseManagerMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseManagerMsApplication.class, args);
	}

}
