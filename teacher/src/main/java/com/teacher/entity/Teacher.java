package com.teacher.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="teacher")
public class Teacher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long teacherId;
	
	private String teacherName;
	
	private String teacherContactNum;
	// Validation annotation for email
    @Email(message = "Invalid email format")
	private String email;
	
	private String address;
	
	private String subject;
	
	private String experience;
	
	private String qualifaction;
	
	private String password;

	public long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherContactNum() {
		return teacherContactNum;
	}

	public void setTeacherContactNum(String teacherContactNum) {
		this.teacherContactNum = teacherContactNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getQualifaction() {
		return qualifaction;
	}

	public void setQualifaction(String qualifaction) {
		this.qualifaction = qualifaction;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
