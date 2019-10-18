package com.altimetrik;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.altimetrik.*"})
@EnableSwagger2
@EnableBatchProcessing
@EnableScheduling
public class UserAuthenticationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthenticationSystemApplication.class, args);
	}
}
