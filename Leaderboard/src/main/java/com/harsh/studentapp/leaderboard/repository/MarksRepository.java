package com.harsh.studentapp.leaderboard.repository;

import org.springframework.data.repository.CrudRepository;

import com.harsh.studentapp.leaderboard.models.Marks;

public interface MarksRepository extends CrudRepository<Marks, Integer>  
{  
	
}  