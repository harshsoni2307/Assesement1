package com.harsh.studentapp.leaderboard.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
//defining class name as Table name  
@Table(name = "student", uniqueConstraints = @UniqueConstraint(columnNames = "studentid"))
public class Student {

	@Id
	@Column
	private int studentid;

	@Column
	private String name;

	public int getStudentid() {
		return studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
