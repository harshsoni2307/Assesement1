package com.harsh.studentapp.leaderboard.repository;

import org.springframework.data.repository.CrudRepository;  
import com.harsh.studentapp.leaderboard.models.Subject;  

public interface SubjectRepository extends CrudRepository<Subject, Integer>  
{  
	
}  