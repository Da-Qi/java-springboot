package com.zhao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication

@EnableTransactionManagement
public class SpringbootBookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBookstoreApplication.class, args);
	}

}
