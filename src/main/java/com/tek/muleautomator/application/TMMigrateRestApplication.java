package com.tek.muleautomator.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.tek.muleautomator")
@EntityScan("com.tek.muleautomator.model")
@EnableJpaRepositories("com.tek.muleautomator.repository")

public class TMMigrateRestApplication  extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TMMigrateRestApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(TMMigrateRestApplication.class, args);
	}

}
