package com.harsh.studentapp.leaderboard.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Leaderboard")
public class Leaderboard {
	  
	@Column
	private int total;  
	
	@Id
	@Column  
	private String studentname;
	public int getTotal() {
		return total;
	}
	public void setTotal(int marks) {
		this.total = marks;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public static Object add(Leaderboard leaderboard1) {
		// TODO Auto-generated method stub
		return null;
	}

}
