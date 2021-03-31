package com.harsh.studentapp.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class StudentAppUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentAppUsersApplication.class, args);
	}

}
