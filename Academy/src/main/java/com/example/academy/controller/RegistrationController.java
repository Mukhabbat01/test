package com.example.academy.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.academy.dto.ReqRegistrationDto;
import com.example.academy.dto.ResRegistrationDto;
import com.example.academy.service.RegistrationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
	private final RegistrationService regService;
	
	@PostMapping("/api/registration/{lecId}/save")
	@ResponseBody
	public String regisToLec(@PathVariable("lecId") int lecId, @RequestBody ReqRegistrationDto registrationDto) {
		return regService.regisToLec(registrationDto, lecId);
	}
	
	
	@GetMapping("/idCheck")
    @ResponseBody
    public boolean idCheck(ReqRegistrationDto registrationDto){
        return regService.isStudent(registrationDto);

    }
	
	@GetMapping("/registrations")
	public String regisShow() {
		return "registration";
	}
	
	@PostMapping("/registrations")
	public String regisShow(Model model, @RequestParam("studentName") String studentName, @RequestParam("studentPhone") String studentPhone) {
		List<ResRegistrationDto> registrations = regService.selectRegistrations(studentName, studentPhone);
		model.addAttribute("registrations", registrations);
		return "registrationView";
	}


	@DeleteMapping("/api/registration/{regisId}/delete")
	@ResponseBody
	public String regisDelete(@PathVariable("regisId") int regisId) {
		return regService.regisDelete(regisId);
	}

}
