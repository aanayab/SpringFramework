package com.truper.spring.data.practicaH.test.jpa;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.truper.spring.data.practicaH.jpa._config.SpringDataJpaConfiguration;
import com.truper.spring.data.practicaH.jpa.repositories.CourseRepository;
import com.truper.spring.data.practicaH.jpa.repositories.StaffRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ActiveProfiles("init-database")
//@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = SpringDataJpaConfiguration.class)
public class CourseRepositoryTest {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StaffRepository staffRepository;

	@Test
	public void courseExample() {

		log.info("courseExample test starts =======================================================");

		// busca todos los cursos
		System.out.println("All courses");
		courseRepository.findAll().forEach(course -> {
			System.out.println(course);
		});
		
		System.out.println();

		// busca los cursos de los instructores con lastname Black
		System.out.println("Courses where Staff Instructor LastName Black");
		courseRepository.findByInstructorMemberLastName("Black").forEach(System.out::println);

		System.out.println();
		
		// busca los instructores(Staff) por nombre del curso
		System.out.println("Staff where CourseName is English 101");
		staffRepository.findInstructorByCourseName("English 101").forEach(System.out::println);
		
		System.out.println();
		
		// busca los instructores(Staff) por nombre del curso (recibe una lista de cursos)
		System.out.println("Staff where CourseName in English 101, English 201");
		staffRepository.findInstructorByCourseNames(Arrays.asList("English 101", "English 201"))
					   .forEach(System.out::println);
		
		System.out.println();
		
		// busca los instructores(Staff) por nombre del curso (recibe una lista de cursos)
		System.out.println("Staff where CourseName in English 101, English 201, English 202");
		staffRepository.findInstructorByCourseNames(Arrays.asList("English 101", "English 201", "English 202"))
					   .forEach(System.out::println);

		log.info("courseExample test ends =======================================================");
	}

}