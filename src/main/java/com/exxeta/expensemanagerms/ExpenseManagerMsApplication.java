package com.exxeta.expensemanagerms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.exxeta")
@EnableFeignClients
public class ExpenseManagerMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseManagerMsApplication.class, args);
	}

}
