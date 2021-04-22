package com.harsh.studentapp.leaderboard.repository;

import org.springframework.data.repository.CrudRepository;

import com.harsh.studentapp.leaderboard.models.Student;

public interface StudentRepository extends CrudRepository<Student, Integer>  
{
	
}  