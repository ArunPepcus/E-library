package com.pepcus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EmployeeMangmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeMangmentApplication.class, args);
	}

}
