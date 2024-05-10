package com.example.academy.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.academy.domain.Lecture;
import com.example.academy.domain.Teacher;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqLectureDto {
	private String title;
	private int maxStudent;
	private String startDtm;
	private String room;
	private String teacherId;
	
	public Lecture lectureToEntity() {
		Lecture lecture = new Lecture();
		Teacher teacher = new Teacher();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(startDtm, formatter);
		
		lecture.setTitle(this.title);
        lecture.setMaxStudent(this.maxStudent);
        lecture.setStartDtm(dateTime); 
        lecture.setRoom(this.room);
        teacher.setTeacherId(this.teacherId);
        lecture.setTeacher(teacher);
		return lecture;
	}
}
