package com.example.academy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.academy.domain.Registration;
import com.example.academy.dto.ResRegistrationDto;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

	boolean existsByLectureLecIdAndStudentPhone(int lecId, String studentPhone);

	@Query(value = "SELECT COUNT(*) FROM registration r WHERE r.lec_id = :lecId", nativeQuery = true)
	int countRegistrationsByLectureId(@Param("lecId") int lecId);

	default boolean isMaxStudentExceeded(int lecId) {
		int registrationCount = countRegistrationsByLectureId(lecId);
		return registrationCount >= getMaxStudentByLectureId(lecId);
	}

	@Query(value = "SELECT max_student FROM lecture WHERE lec_id = :lecId", nativeQuery = true)
	int getMaxStudentByLectureId(@Param("lecId") int lecId);

	
	
	@Query(value="SELECT l.title as title, l.max_student as maxStudent, l.start_dtm as startDtm, l.room as room, l.lec_id as lecId, t.teacher_name as teacherName "
			+ "FROM Registration r  "
			+ "left join lecture l on l.lec_id = r.lec_id "
			+ "left join teacher t on t.teacher_id = l.teacher_id "
			+ "WHERE r.student_name = :studentName "
			+ "AND r.student_phone = :studentPhone "
			+ "AND l.start_dtm > now()", nativeQuery = true)
	List<ResRegistrationDto> findRegistrationByStudentNameAndPhone(@Param("studentName") String studentName, @Param("studentPhone") String studentPhone);

	boolean findByStudentNameAndStudentPhone(String studentName, String studentPhone);

	
	

	
	
}
