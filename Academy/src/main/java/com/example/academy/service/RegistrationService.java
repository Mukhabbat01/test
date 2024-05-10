package com.example.academy.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.academy.domain.Lecture;
import com.example.academy.domain.Registration;
import com.example.academy.dto.ReqRegistrationDto;
import com.example.academy.dto.ResRegistrationDto;
import com.example.academy.repository.LectureRepository;
import com.example.academy.repository.RegistrationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationService {
	private final RegistrationRepository registrationRepository;
	private final LectureRepository lectureRepository;

	public String regisToLec(ReqRegistrationDto registrationDto, int lecId) {
		Optional<Lecture> optionalLecture = lectureRepository.findById(lecId);

		if (optionalLecture.isPresent()) {
			Lecture lecture = optionalLecture.get();

			if (!lectureRepository.existsByLecIdAndStartDtmAfter(lecId, LocalDateTime.now())) {
				return "수강 신청 기간이 지났습니다!";
			} else if (registrationRepository.existsByLectureLecIdAndStudentPhone(lecId,
					registrationDto.getStudentPhone())) {
				return "이미 수강 신청한 특강입니다!";
			} else if (registrationRepository.isMaxStudentExceeded(lecId)) {
				return "최대인원 초과로 신청할 수 없습니다";
			} else {
				Registration registration = registrationDto.regisToEntity();
				registration.setLecture(lecture);
				registrationRepository.save(registration);
				return "수강 신청 성공~";
			}
		} else {
			return "해당 강의를 찾을 수 없습니다!";

		}
	}

	public List<ResRegistrationDto> selectRegistrations(String studentName, String studentPhone) {
		return registrationRepository.findRegistrationByStudentNameAndPhone(studentName, studentPhone);
	}

	public boolean isStudent(ReqRegistrationDto registrationDto) {
		return registrationRepository.findByStudentNameAndStudentPhone(registrationDto.getStudentName(),
				registrationDto.getStudentPhone());
	}

	public String regisDelete(int regisId) {
		try {
			Optional<Registration> registration = registrationRepository.findById(regisId);
			if (registration.isPresent()) {

				boolean isLectureStarted = registration.get().getLecture().getStartDtm().isBefore(LocalDateTime.now());
				if (!isLectureStarted) {

					registrationRepository.deleteById(regisId);
					return "수강 신청이 취소되었습니다~";
				} else {
					return "해당 수업은 이미 시작되어 취소할 수 없습니다.";
				}
			} else {
				return "해당하는 수강 신청이 존재하지 않습니다.";
			}
		} catch (Exception e) {
			return "취소 실패! 에러 메시지: " + e.getMessage();
		}
	}

}
