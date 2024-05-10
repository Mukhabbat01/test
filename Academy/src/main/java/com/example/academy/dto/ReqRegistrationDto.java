package com.example.academy.dto;

import com.example.academy.domain.Lecture;
import com.example.academy.domain.Registration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqRegistrationDto {
	private String studentPhone;
	private String studentName;
	private int lecId;
	
	public Registration regisToEntity() {
		Registration registration = new Registration(); 
		Lecture lecture = new Lecture();
		
		registration.setStudentPhone(this.studentPhone);
		registration.setStudentName(this.studentName);
		
		lecture.setLecId(this.lecId);
		registration.setLecture(lecture);
		return registration;
	}
}
