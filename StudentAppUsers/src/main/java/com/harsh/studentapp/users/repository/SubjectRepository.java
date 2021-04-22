package com.harsh.studentapp.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harsh.studentapp.users.models.ESubject;
import com.harsh.studentapp.users.models.Subject;  

public interface SubjectRepository extends JpaRepository<Subject, Integer>  
{  
	Optional<Subject> findByName(ESubject name);
}  