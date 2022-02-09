package com.truper.spring.data.practicaH.test.jpa.demo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.truper.spring.data.practicaH.jpa._config.SpringDataJpaConfiguration;
import com.truper.spring.data.practicaH.jpa.entity.Course;
import com.truper.spring.data.practicaH.jpa.entity.Department;
import com.truper.spring.data.practicaH.jpa.entity.Person;
import com.truper.spring.data.practicaH.jpa.entity.Staff;
import com.truper.spring.data.practicaH.jpa.entity.Student;
import com.truper.spring.data.practicaH.jpa.repositories.CourseRepository;
import com.truper.spring.data.practicaH.jpa.repositories.DepartmentRepository;
import com.truper.spring.data.practicaH.jpa.repositories.StaffRepository;
import com.truper.spring.data.practicaH.jpa.repositories.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ActiveProfiles({ "init-database" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = SpringDataJpaConfiguration.class)
public class SpringDataJpaQueryDemosTest {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	StaffRepository staffRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	DepartmentRepository departmentRepository;

	// Simple Property Expression Queries.
	@Test
	public void simpleQueryExamples() {

		log.info("simpleQueryExamples test starts =======================================================");

		System.out.println("\nFind students born at 1988-02-13");
		LocalDate date = LocalDate.of(1988, 02, 13);
		// busca los estudiantes con fecha de nacimiento "date"
		studentRepository.findByBirthday(date).forEach(s -> System.out.println(s));

		System.out.println("\nFind full time students");
		// busca los estudiantes de tiempo completo
		studentRepository.findByFullTime(true).forEach(s -> System.out.println(s));
		
		System.out.println("\nFind NOT full time students");
		// busca los estudiantes de tiempo completo
		studentRepository.findByFullTimeFalse().forEach(s -> System.out.println(s));

		System.out.println("\nFind students with 'doe' as last name");
		// busca los estudiantes por "lastName"
		studentRepository.findByAttendeeLastName("doe").forEach(s -> System.out.println(s));

		log.info("simpleQueryExamples test ends =======================================================");
	}

	// Advanced Property Expression Queries
	@Test
	public void intermediateQueryExamples() {

		log.info("intermediateQueryExamples test starts =======================================================");

		Student janeDoe = studentRepository.findByAttendee(new Person("jane", "doe")); // busca el estudiante por "Attendee"
		System.out.println("Find students by name with Person Object \n" + janeDoe + "\n");

		Student janeDoeTraverse = studentRepository.findByAttendeeFirstNameAndAttendeeLastName("jane","doe"); // busca el estudiante por "attendeeFirstName" y "attendeeLastName" 
		System.out.println("Find students by name and traverse entities \n"
				+ janeDoeTraverse + "\n");

		System.out.println("Find Students Greather than 1987-07-22");
		LocalDate date = LocalDate.of(1987, 07, 22);
		// busca los estudiantes con fecha de nacimiento mayor a "date"
		studentRepository.findByBirthdayGreaterThan(date).forEach(System.out::println);

		System.out.println("\nFind Students under 1987-07-22");
		// busca los estudiantes con fecha de nacimiento menor a "date"
		studentRepository.findByBirthdayLessThan(date).forEach(System.out::println);

		System.out.println("\nFind Students with 'DOE' as last name, despite the case");
		// busca los estudiantes con "lastName" ignorando mayusculas y/o minusculas
		studentRepository.findByAttendeeLastNameIgnoreCase("Doe").forEach(System.out::println);
		studentRepository.findByAttendee_LastNameIgnoreCase("DOE").forEach(System.out::println);

		System.out.println("\nFind Students with an 'i' in the last name");
		// busca los estudiantes con "lastName" Like 
		studentRepository.findByAttendeeLastNameLike("%i%").forEach(System.out::println);
		studentRepository.findByAttendeeLastNameContaining("i").forEach(System.out::println);

		// busca el primer estudiante en orden alfabetico ascendente por "lastName"
		System.out.println(
				"\nFind first Student in alphabet \n" + studentRepository.findFirstByOrderByAttendeeLastNameAsc().get() );

		// busca el estudiante mas joven
		System.out.println("\nFind yungest Student \n" + studentRepository.findFirstByOrderByBirthdayDesc());

		// busca el estudiante mas viejo
		System.out.println("\nFind oldest Student \n" + studentRepository.findFirstByOrderByBirthdayAsc());

		System.out.println("\nFind 3 yungest Students");
		// busca los tres estudiantes mas jovenes
		studentRepository.findTop3ByOrderByBirthdayDesc().forEach(System.out::println);

		System.out.println("\nFind 3 oldest Students");
		// busca los tres estudiantes mas viejos
		studentRepository.findTop3ByOrderByBirthdayAsc().forEach(System.out::println);

		log.info("intermediateQueryExamples test ends =======================================================");
	}

	// Complex Queries with @Query and JPQL
	@Test
	public void jpqlQueries() {

		log.info("jpqlQueries test starts =======================================================");

		// *******Method Simplification*******

		System.out.println("\nFind Courses where Jones is the department Chair with Property Expression");
		// busca el curso donde el "lastName" igual a "Jones" es el jefe de departamento (derived query)
		courseRepository.findByDepartmentChairMemberLastName("Jones").forEach(System.out::println);

		System.out.println("\nFind Courses where Jones is the department Chair with @Query");
		// busca el curso donde el "lastName" igual a "Jones" es el jefe de departamento (@Query JPQL)
		courseRepository.findByChairLastName("Jones").forEach(System.out::println);

		// *******Complex Queries********

		Optional<Course> english101Optional = courseRepository.findByName("English 101"); // busca el curso "English 101" por nombre
		Course english101 = english101Optional.orElse(null);
		System.out.println("\nFind Courses by name English 101 \n" + english101);
		
		System.out.println("\nFind Courses by name English 101 \n" + courseRepository.findByCourseName("English 101").get());

		System.out.println("\nFind Courses where English 101 is a prerequisite");
		// busca los cursos donde el curso "English 101" es prerequisito
		courseRepository.findCoursesByPrerequisiteCoursId(english101.getId()).forEach(System.out::println);
		courseRepository.findByPrerequisitesNameIs(english101.getName()).forEach(System.out::println);

		// busca el curso "English 101" utilizando su vista "CourseView"
		System.out.println("\nCourseView for English 101 \n" + courseRepository.findCourseByCourseViewName("English 201"));

		log.info("jpqlQueries test ends =======================================================");
	}

	// Queries that use Paging and Sorting
	@Test
	public void pagingAndSortingQueries() {

		log.info("pagingAndSortingQueries test starts =======================================================");

		System.out.println("\nFind all 3-credit courses");
		// busca los cursos con 3 creditos
		List<Course> list3Credits = courseRepository.findByCredits(3);
		list3Credits.forEach(System.out::println);

		int total3CreditsCourse = list3Credits.size(); // cuenta los cursos con 3 creditos
		String[] th = { "st", "nd", "rd", "th" }; 

		for (int i = 0; i < Math.ceil(total3CreditsCourse / 4D); i++) {
			System.out.println("\nFind " + (i + 1) + th[(i < 3) ? i : 3] + " 3-credit courses of " + total3CreditsCourse
					+ ", sort by credit, then course name");
			
			Pageable page = PageRequest.of(i, 4, Sort.Direction.ASC, "credits", "name");
			Page<Course> courses = courseRepository.findByCredits(3, page); // busca la pagina "i" de 4 elementos por pagina de los cursos con 3 creditos
									     // ordena por "credits" y "name" de forma ascendente

			courses.forEach(System.out::println);
		}

		System.out.println("\nFind all staff members, sort alphabetically by last name");
		
		Sort sortByLastName = new Sort(Sort.Direction.ASC, "member.lastName", "member.firstName"); // define un objeto Sort que ordene ascendentemente por "lastName" y "firstName"
		
		// busca todos los Staff ordenados por el objeto Sort previamente definido
		staffRepository.findAll(sortByLastName).forEach(System.out::println);

		Page<Staff> members = staffRepository.findAll(PageRequest.of(0, 5, sortByLastName)); // busca la primer pagina de 5 elementos de los Staff 
									// utilizando el ordenamiento "sortByLastName" definido previamente
		System.out.println("\nTotal number of staff members=" + members.getTotalElements());
		System.out.println("Total number of 5-element-pages=" + members.getTotalPages());
		System.out.println("Find first 5 Staff members, sort alphabetically by last name");
		members.forEach(System.out::println);

		log.info("pagingAndSortingQueries test ends =======================================================");
	}
	
	@Test
	public void queryByExample() {
		log.info("queryByExample test starts =======================================================");
		
		Optional<Department> department = departmentRepository.findOne(
				Example.of(new Department("Humanities", null)));
		
		System.out.println("\n Find Department with name 'Humanities' \n" + department.get());
		
		List<Department> departmentList = departmentRepository.findAll(
				Example.of(new Department(null, new Staff(new Person("John", null)))));
		
		System.out.println("\n Find Department with chair firstName is 'John' \n");
		departmentList.forEach(System.out::println);
	}

	@Before
	@After
	public void printBanner() {
		System.out.println("*************************************************************************************");
	}

}