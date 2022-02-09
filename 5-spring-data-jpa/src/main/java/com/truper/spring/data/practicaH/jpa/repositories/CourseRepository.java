package com.truper.spring.data.practicaH.jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.truper.spring.data.practicaH.jpa.entity.Course;
import com.truper.spring.data.practicaH.jpa.view.CourseView;

// Extiende de CrudRepository
public interface CourseRepository extends JpaRepository<Course, Long> {

	// Define los metodos conforme la practica lo requiera (guiado por instructor)
	List<Course> findByInstructorMemberLastName(String lastName);

	List<Course> findByDepartmentChairMemberLastName(String chairLastName);

	@Query("SELECT c FROM Course c WHERE c.department.chair.member.lastName = :chairLastName")
	List<Course> findByChairLastName(@Param("chairLastName") String chairLastName);

	Optional<Course> findByName(String name);

	@Query("SELECT c FROM Course c WHERE c.name = :courseName")
	Optional<Course> findByCourseName(@Param("courseName") String courseName);

	@Query("SELECT c FROM Course c JOIN c.prerequisites p WHERE p.id = ?1")
	List<Course> findCoursesByPrerequisiteCoursId(Long id);

	List<Course> findByPrerequisitesNameIs(String courseName);

	@Query("SELECT new com.truper.spring.data.practicaH.jpa.view.CourseView("
			+ "c.name, c.instructor.member.lastName, c.department.name) " + "FROM Course c WHERE c.name = ?1")
	CourseView findCourseByCourseViewName(String courseName);

	List<Course> findByCredits(Integer credits);

	Page<Course> findByCredits(Integer credits, Pageable page);
}
