package com.example.academy.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.academy.domain.Lecture;
import com.example.academy.dto.ResLectureDto;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {
	
	boolean existsByTeacherTeacherIdAndStartDtm(String teacherId, LocalDateTime startDtm);
	
	@Query(value = "select l.lec_id as lecId,  l.max_student as maxStudent, l.room , l.start_dtm as startDtm, t.teacher_name as teacherName, l.title "
				 + "from lecture l "
				 + "left join teacher t on t.teacher_id = l.teacher_id "
				 + "where ( select count(*) from registration r where r.lec_id  = l.lec_id) < l.max_student "
				 + "and l.start_dtm > now();", nativeQuery = true)
	public List<ResLectureDto> selectLectures();

	boolean existsByLecIdAndStartDtmAfter(int lecId, LocalDateTime now);

	


	
	

}