package com.harsh.studentapp.leaderboard.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
//defining class name as Table name  
@Table(name = "marks", uniqueConstraints = @UniqueConstraint(columnNames = "marksid"))
public class Marks {
	@Id
	@Column
	private int marksid;

	public int getMarksid() {
		return marksid;
	}

	public void setMarksid(int marksid) {
		this.marksid = marksid;
	}

	@Column
	private int studentid;

	@Column
	private int subjectid;

	@Column
	private int marks;

	public int getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(int subjectid) {
		this.subjectid = subjectid;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public int getStudentid() {
		return studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

}