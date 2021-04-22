package com.harsh.studentapp.leaderboard.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

//mark class as an Entity   
@Entity  
//defining class name as Table name  
@Table(name = "subject", uniqueConstraints =  @UniqueConstraint(columnNames = "subjectid"))
public class Subject {
	
	@Id  
	@Column  
	private int subjectid;  
	@Column  
	private String subjectname;
	public int getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(int subjectid) {
		this.subjectid = subjectid;
	}
	public String getSubjectname() {
		return subjectname;
	}
	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}  
}
