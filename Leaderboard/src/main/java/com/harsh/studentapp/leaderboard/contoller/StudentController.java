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

import com.harsh.studentapp.leaderboard.models.Student;
import com.harsh.studentapp.leaderboard.service.StudentService;

@RestController
public class StudentController {
	@Autowired  
	StudentService StudentService;  
	
	@GetMapping("/student")  
	private List<Student> getAllStudent()   
	{  
	return StudentService.getAllStudent();  
	}  

	@GetMapping("/student/{studentid}")  
	private Student getStudent(@PathVariable("Studentid") int studentid)   
	{  
	return StudentService.getStudentById(studentid);  
	}  
	@DeleteMapping("/student/{studentid}")  
	private void deleteStudent(@PathVariable("Studentid") int studentid)   
	{  
	StudentService.delete(studentid);  
	}  
	@PostMapping("/student")  
	private int saveStudent(@RequestBody Student student)   
	{  
	StudentService.saveOrUpdate(student);  
	return student.getStudentid();  
	}  
	@PutMapping("/student")  
	private Student update(@RequestBody Student student)   
	{  
	StudentService.saveOrUpdate(student);  
	return student;  
	}  

	
}