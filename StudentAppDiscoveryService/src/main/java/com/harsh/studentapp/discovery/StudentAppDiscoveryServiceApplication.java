package com.harsh.studentapp.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class StudentAppDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentAppDiscoveryServiceApplication.class, args);
	}

}
