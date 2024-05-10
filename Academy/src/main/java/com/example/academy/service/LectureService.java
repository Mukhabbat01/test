package com.example.academy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.academy.domain.Lecture;
import com.example.academy.dto.ReqLectureDto;
import com.example.academy.dto.ResLectureDto;
import com.example.academy.repository.LectureRepository;
import com.example.academy.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LectureService {
	private final LectureRepository lectureRepository;
	private final TeacherRepository teacherRepository;

	public String lectureSave(ReqLectureDto lectureDto) {
		Lecture lecture = lectureDto.lectureToEntity();

		if (!teacherRepository.existsById(lectureDto.getTeacherId())) {
			return "강사 아이디를 찾을 수 없습니다!";

		} else if (lectureRepository.existsByTeacherTeacherIdAndStartDtm(lectureDto.getTeacherId(),
				lecture.getStartDtm())) {
			return "강사는 동일 시간에 두 과목에 배정될 수 없습니다.";
		} else {
			lectureRepository.save(lecture);
			return "강의가 등록되었습니다~";
		}
	}

	public String lectureUpdate(ReqLectureDto lectureDto, int lecId) {
		Lecture updatedLecture = lectureDto.lectureToEntity();
		Optional<Lecture> existingLectureOptional = lectureRepository.findById(lecId);
		if (existingLectureOptional.isEmpty()) {
			return "특강 아이디를 찾을 수 없습니다!";
		}
		Lecture existingLecture = existingLectureOptional.get();
		if (existingLecture.getLecId() != lecId) {
			return "요청된 특강 아이디가 일치하지 않습니다!";
		}
		updatedLecture.setLecId(lecId);
		lectureRepository.save(updatedLecture);
		return "특강 정보가 수정되었습니다~";
	}

	
	public String lectureDelete(int lecId) {
		if (lectureRepository.existsById(lecId)) {
			lectureRepository.deleteById(lecId);
			return "삭제되었습니다~";
		} else {
			return "삭제 실패!";
		}
	}

	
	public List<ResLectureDto> lecList() {
		return lectureRepository.selectLectures();
	}
}
