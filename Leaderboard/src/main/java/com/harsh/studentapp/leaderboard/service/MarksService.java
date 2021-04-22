package com.harsh.studentapp.leaderboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harsh.studentapp.leaderboard.models.Marks;
import com.harsh.studentapp.leaderboard.repository.MarksRepository;

@Service
public class MarksService {
	@Autowired  
	MarksRepository MarksRepository;  
	public List<Marks> getAllMarks()   
	{  
	List<Marks> Marks = new ArrayList<Marks>();  
	MarksRepository.findAll().forEach(Marks1 -> Marks.add(Marks1));  
	return Marks;  
	}  
	public Marks getMarksById(int id)   
	{  
	return MarksRepository.findById(id).get();  
	}  
	public void saveOrUpdate(Marks Marks)   
	{  
	MarksRepository.save(Marks);  
	}  
	public void delete(int id)   
	{  
	MarksRepository.deleteById(id);  
	}  
	public void update(Marks Marks, int bookid)   
	{  
	MarksRepository.save(Marks);  
	}  

}