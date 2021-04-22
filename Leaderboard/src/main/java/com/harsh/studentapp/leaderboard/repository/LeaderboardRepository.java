package com.harsh.studentapp.leaderboard.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harsh.studentapp.leaderboard.models.Leaderboard;

@Repository
public interface LeaderboardRepository  extends CrudRepository<Leaderboard,String>{
	
	@Modifying
	@Query(value=("INSERT INTO leaderboard (studentname, total) SELECT s.name, SUM(m.marks) FROM marks m INNER JOIN student s ON s.studentid = m.studentid GROUP BY s.studentid;"),nativeQuery=true)
	@Transactional
	void addRecord();
	
	@Query(value="select * from leaderboard order by total desc limit 10", nativeQuery=true)
	List<Leaderboard> getRecords();
	
	@Modifying
	@Query(value="delete from leaderboard",nativeQuery=true)
	@Transactional
	void deleteRecord();
	

}
