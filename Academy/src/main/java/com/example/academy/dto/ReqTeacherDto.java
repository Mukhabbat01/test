package com.example.academy.dto;

import com.example.academy.domain.Teacher;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqTeacherDto {
	private String teacherId;
	private String teacherName;
	
	public Teacher teacherToEntity() {
		Teacher teacher = new Teacher();
		teacher.setTeacherId(this.teacherId);
		teacher.setTeacherName(this.teacherName);
		
		return teacher;
	}
}
