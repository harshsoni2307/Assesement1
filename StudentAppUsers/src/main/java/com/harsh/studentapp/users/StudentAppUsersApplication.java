package com.harsh.studentapp.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableCaching
@EnableDiscoveryClient
@EnableSwagger2
public class StudentAppUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentAppUsersApplication.class, args);
	}

}
