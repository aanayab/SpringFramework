package com.truper.spring.data.practicaH.jpa.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truper.spring.data.practicaH.jpa.entity.Person;
import com.truper.spring.data.practicaH.jpa.entity.Student;

// Extiende de JpaRepository
public interface StudentRepository extends JpaRepository<Student, Long> {

	// Define los metodos conforme la practica lo requiera (guiado por instructor)
	List<Student> findByBirthday(LocalDate date);

	List<Student> findByFullTime(boolean fullTime);

	List<Student> findByFullTimeFalse();

	List<Student> findByAttendeeLastName(String lastName);

	Student findByAttendee(Person attendee);

	Student findByAttendeeFirstNameAndAttendeeLastName(String firstName, String lastName);

	List<Student> findByBirthdayGreaterThan(LocalDate birthday);

	List<Student> findByBirthdayLessThan(LocalDate birthday);

	List<Student> findByAttendeeLastNameIgnoreCase(String lastName);

	List<Student> findByAttendee_LastNameIgnoreCase(String lastName);

	List<Student> findByAttendeeLastNameLike(String lastName);

	List<Student> findByAttendeeLastNameContaining(String lastName);

	Optional<Student> findFirstByOrderByAttendeeLastNameAsc();

	Student findFirstByOrderByBirthdayDesc();

	Student findFirstByOrderByBirthdayAsc();

	List<Student> findTop3ByOrderByBirthdayDesc();

	List<Student> findTop3ByOrderByBirthdayAsc();

	List<Student> findDistinctByAttendeeLastName(String lastName);

	List<Student> findByCoursesNameIn(List<String> coursesNames);

	List<Student> findDistinctAlumnoByCoursesNameIn(List<String> coursesNames);

}