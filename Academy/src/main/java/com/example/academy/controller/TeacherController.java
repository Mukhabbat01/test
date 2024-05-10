package com.example.academy.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.academy.dto.ReqTeacherDto;
import com.example.academy.service.TeacherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TeacherController {
	private final TeacherService teacherService;
	
	@PostMapping("/teacher/save")
	public String teacherSave(@RequestBody ReqTeacherDto teacherDto) {
		return teacherService.teacherSave(teacherDto);
	}
	
	@PatchMapping("/teacher/update")
	public String teacherUpdate(@RequestBody ReqTeacherDto teacherDto) {
		return teacherService.teacherUpdate(teacherDto);
	}
	
	
	@DeleteMapping("/teacher/{teacherId}/delete")
	public String teacherDelete(@PathVariable("teacherId")String teacherId) {
		return teacherService.teacherDelete(teacherId);
	}
}
