package com.harsh.studentapp.leaderboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harsh.studentapp.leaderboard.models.Leaderboard;
import com.harsh.studentapp.leaderboard.repository.LeaderboardRepository;

@Service
public class LeaderboardService {
	
	@Autowired  
	LeaderboardRepository leaderboardRepository;

	public void add() {
		leaderboardRepository.addRecord();
		
	}

	public void delete() {
		leaderboardRepository.deleteRecord();
		
	}

	public List<Leaderboard> top10() {
		return(leaderboardRepository.getRecords());
		
	}


}
