package com.example.academy.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.academy.dto.ReqLectureDto;
import com.example.academy.dto.ResLectureDto;
import com.example.academy.service.LectureService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LectureController {
	private final LectureService lectureService;
	
//	@PostMapping("/api/lecture/save")
//	@ResponseBody
//	public String lectureSave(@RequestBody ReqLectureDto lectureDto) {
//		return lectureService.lectureSave(lectureDto);
//	}
	
	
	@PatchMapping("/api/lecture/{lecId}/update")
	@ResponseBody
	public String lectureUpdate(@PathVariable("lecId") int lecId, @RequestBody ReqLectureDto lectureDto) {
		return lectureService.lectureUpdate(lectureDto, lecId);
	}
	
	
	@DeleteMapping("/api/lecture/{lecId}/delete")
	@ResponseBody
	public String lectureDelete(@PathVariable("lecId") int lecId) {
		return lectureService.lectureDelete(lecId);
	}
	
	
	
	@GetMapping("/lectures")
	public String letures(Model model) {
		List<ResLectureDto> lecture = lectureService.lecList(); 
	    model.addAttribute("lecture", lecture);
		return "lectureView";
	}
}
