package com.harsh.studentapp.leaderboard.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.harsh.studentapp.leaderboard.models.Subject;
import com.harsh.studentapp.leaderboard.service.SubjectService;


@RestController
public class SubjectController {
	@Autowired  
	SubjectService subjectService;  
	
	@GetMapping("/subject")  
	private List<Subject> getAllSubject()   
	{  
	return subjectService.getAllsubject();  
	}  

	@GetMapping("/subject/{subjectid}")  
	private Subject getSubject(@PathVariable("subjectid") int subjectid)   
	{  
	return subjectService.getsubjectById(subjectid);  
	}  
	@DeleteMapping("/subject/{subjectid}")  
	private void deleteBook(@PathVariable("subjectid") int subjectid)   
	{  
	subjectService.delete(subjectid);  
	}  
	@PostMapping("/subject")  
	private int saveBook(@RequestBody Subject Subject)   
	{  
	subjectService.saveOrUpdate(Subject);  
	return Subject.getSubjectid();  
	}  
	@PutMapping("/subject")  
	private Subject update(@RequestBody Subject Subject)   
	{  
	subjectService.saveOrUpdate(Subject);  
	return Subject;  
	}  

	
}
