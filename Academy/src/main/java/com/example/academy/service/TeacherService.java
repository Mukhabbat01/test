package com.example.academy.service;

import org.springframework.stereotype.Service;

import com.example.academy.dto.ReqTeacherDto;
import com.example.academy.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherService {
	private final TeacherRepository teacherRes;
	
	public String teacherSave(ReqTeacherDto teacherDto) {
		if(teacherRes.existsById(teacherDto.getTeacherId())) {
			return "이미 등록된 아이디입니다!";
		}else {
			teacherRes.save(teacherDto.teacherToEntity());
		}
		return "등록 완료되었습니다~";
	}
	
	
	public String teacherUpdate(ReqTeacherDto teacherDto) {
		if(teacherRes.existsById(teacherDto.getTeacherId())) {
			teacherRes.save(teacherDto.teacherToEntity());
			return "이름이 수정되었습니다~";
		}else {
			return "아이디를 찾을수 없습니다!";
		}
	}
	
	
	public String teacherDelete(String teacherId) {
		if(teacherRes.existsById(teacherId)) {
			teacherRes.deleteById(teacherId);
			return "삭제 완료되었습니다~";
		}else {
			return "아이디를 찾을수 없습니다!";
		}
	}

	
}
