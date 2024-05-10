package com.example.academy.dto;

import java.time.LocalDateTime;

public interface ResLectureDto {
	public String getTitle();
	public int getMaxStudent();
	public LocalDateTime getStartDtm();
	public String getRoom();
	public String getTeacherName();
	public int getLecId();
	
}
