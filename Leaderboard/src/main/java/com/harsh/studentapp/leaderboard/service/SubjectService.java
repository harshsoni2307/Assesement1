package com.harsh.studentapp.leaderboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harsh.studentapp.leaderboard.models.Subject;
import com.harsh.studentapp.leaderboard.repository.SubjectRepository;


@Service
public class SubjectService {
	@Autowired  
	SubjectRepository subjectRepository;  
	public List<Subject> getAllsubject()   
	{  
	List<Subject> subject = new ArrayList<Subject>();  
	subjectRepository.findAll().forEach(subject1 -> subject.add(subject1));  
	return subject;  
	}  
	public Subject getsubjectById(int id)   
	{  
	return subjectRepository.findById(id).get();  
	}  
	public void saveOrUpdate(Subject subject)   
	{  
	subjectRepository.save(subject);  
	}  
	public void delete(int id)   
	{  
	subjectRepository.deleteById(id);  
	}  
	public void update(Subject subject, int bookid)   
	{  
	subjectRepository.save(subject);  
	}  

}
