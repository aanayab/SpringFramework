package com.truper.spring.data.practicaH.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.truper.spring.data.practicaH.jpa.entity.Staff;

// Extiende de PagingAndSortingRepository, despues extiende de StaffCustomRepository
public interface StaffRepository extends PagingAndSortingRepository<Staff, Long>, StaffCustomRepository {

	// Define los metodos conforme la practica lo requiera (guiado por instructor)
	
	@Query(value = "SELECT s.* FROM STAFF_MEMBER_TBL s, COURSE_TBL c "
				 + "WHERE c.COURSE_NAME = :courseName AND s.id = c.instructor_id", nativeQuery = true)
	List<Staff> findInstructorByCourseName(@Param("courseName") String courseName);
	
	@Query(value = "SELECT s.* FROM STAFF_MEMBER_TBL s, COURSE_TBL c "
			 + "WHERE c.COURSE_NAME IN :courseNames AND s.id = c.instructor_id", nativeQuery = true)
	List<Staff> findInstructorByCourseNames(@Param("courseNames") List<String> courseNames);

	@Query("SELECT s FROM Staff s WHERE s.member.firstName = :first AND s.member.lastName = :last")
	List<Staff> selectStaffByFullName(@Param("last") String lastName, @Param("first") String firstName);
	
	@Query(value= "SELECT s.* FROM STAFF_MEMBER_TBL s WHERE s.STUDENT_FIRST_NAME = :first AND s.STUDENT_LAST_NAME = :last", nativeQuery = true)
	List<Staff> selectNativeStaffByFullName(@Param("last") String lastName, @Param("first") String firstName);
}

