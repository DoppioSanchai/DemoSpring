package com.specification.Spec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"controllers","services","data", "repositories"})
public class SpecApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpecApplication.class, args);
	}

}
