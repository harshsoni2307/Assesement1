package com.harsh.studentapp.leaderboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harsh.studentapp.leaderboard.models.Student;
import com.harsh.studentapp.leaderboard.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired  
	StudentRepository studentRepository;  
	public List<Student> getAllStudent()   
	{  
	List<Student> Student = new ArrayList<Student>();  
	studentRepository.findAll().forEach(Student1 -> Student.add(Student1));  
	return Student;  
	}  
	public Student getStudentById(int id)   
	{  
	return studentRepository.findById(id).get();  
	}  
	public void saveOrUpdate(Student student)   
	{  
	studentRepository.save(student);  
	}  
	public void delete(int id)   
	{  
	studentRepository.deleteById(id);  
	}  
	public void update(Student student, int bookid)   
	{  
	studentRepository.save(student);  
	}  

}
