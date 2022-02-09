package com.truper.spring.data.practicaH.test.jpa;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
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
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = SpringDataJpaConfiguration.class)
public class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private CourseRepository courseRepository;


	@Test
	public void simpleStudentCrudExample() {

		log.info("simpleStudentCrudExample test starts =======================================================");
		
		// Staff
		Staff deanJones = staffRepository.save(createStaffJohnJones()); // almacena el Staff createStaffJohnJones
		Staff profBlack = staffRepository.save(createStaffJackBlack()); // almacena el Staff createStaffJackBlack

		// Departments
		Department humanities = departmentRepository.save(createDepartmentHumanities(deanJones)); // almacena el Departamento createDepartmentHumanities con deanJones como jefe de depto

		Course english101 = courseRepository.save(createCourseEnglish101(profBlack, humanities)); // almacena el Curso createCourseEnglish101 con profBlack como profesor del depto humanities
		Course english202 = courseRepository.save(createCourseEnglish202(profBlack, humanities)); // almacena el Curso createCourseEnglish202 con profBlack como profesor del depto humanities

		boolean fullTime = true;

		// almacena janeDoe dos veces
		studentRepository.save(janeDoe(fullTime));
		studentRepository.save(janeDoe(fullTime));
		// almacena johnDoe
		studentRepository.save(johnDoe(fullTime));
		// almacena mikeSmith
		studentRepository.save(mikeSmith(fullTime));
		// almacena allyKim
		studentRepository.save(allyKim(fullTime));

		System.out.println("\n*************Original Students*************");
		List<Student> students = studentRepository.findAll(); // busca todos los Student
		
		Assert.assertEquals(5, students.size());
		
		students.forEach(System.out::println);
		
		// age up the students
		@SuppressWarnings("unused")
		List<Student> studentsAltered = studentRepository.findAll()// busca todos los Student y modificalos,
								.stream()
								.peek(s -> s.setBirthday(s.getBirthday().minusYears(1)))
								.peek(studentRepository::save) // sumales un anio a su edad y actualizalos en bd.
								.collect(Collectors.toList()); // opera todo en un Stream.

		System.out.println("\n*************Students a year older*************");
		List<Student> studentsYearOlder = studentRepository.findAll(); // busca todos los Student
		
		Assert.assertEquals(5, studentsYearOlder.size());
		
		studentsYearOlder.forEach(System.out::println);

		System.out.println("\n*************Distinct doe lastname Students*************");

		List<Student> doeStudents = studentRepository.findDistinctByAttendeeLastName("doe"); // busca los distintos Student por attendeeLastName

		doeStudents.forEach(System.out::println);

		System.out.println(
				"\n*************doe lastname Students enrolled in courses English 101 and English 202*************");

		// Al alumno jonhDoe enrolar los cursos english101 y english202. almacenar los cambios
		Student johnDoe = studentRepository.findByAttendeeFirstNameAndAttendeeLastName("john", "doe");
		johnDoe.getCourses().add(english101);
		johnDoe.getCourses().add(english202);
		studentRepository.save(johnDoe);

		List<Student> doeStudentsEnrolled = studentRepository.findByCoursesNameIn(
				Arrays.asList("English 101","English 202")); // buscar los Student enrolados por nombre de los cursos (lista)

		doeStudentsEnrolled.forEach(System.out::println);

		System.out.println(
				"\n*************Distinct doe lastname Students enrolled in courses English 101 and English 202*************");

		List<Student> distinctDoeStudentsEnrolled = studentRepository.findDistinctAlumnoByCoursesNameIn(
				Arrays.asList("English 101","English 202")); // buscar los Student (distintos) enrolados por nombre de los cursos (lista)

		distinctDoeStudentsEnrolled.forEach(System.out::println);
		
		// borra todos los Student
		studentRepository.deleteAllInBatch();
		
		System.out.println("\n*************Students removed*************");
		List<Student> deletedStudents = studentRepository.findAll(); // busca todos los Student
		
		Assert.assertEquals(0, deletedStudents.size());
		
		deletedStudents.forEach(System.out::println);

		log.info("simpleStudentCrudExample test ends =======================================================");
	}

	private Student allyKim(boolean fullTime) {
		return new Student(new Person("ally", "kim"), !fullTime, LocalDate.of(1987, 07, 22));
	}

	private Student mikeSmith(boolean fullTime) {
		return new Student(new Person("mike", "smith"), fullTime, LocalDate.of(1987, 12, 06));
	}

	private Student johnDoe(boolean fullTime) {
		return new Student(new Person("john", "doe"), fullTime, LocalDate.of(1987, 06, 23));
	}

	private Student janeDoe(boolean fullTime) {
		return new Student(new Person("jane", "doe"), fullTime, LocalDate.of(1988, 02, 13));
	}

	private Staff createStaffJackBlack() {
		return new Staff(new Person("Jack", "Black"));
	}

	private Staff createStaffJohnJones() {
		return new Staff(new Person("John", "Jones"));
	}

	private Department createDepartmentHumanities(Staff deanJones) {
		return new Department("Humanities", deanJones);
	}

	private Course createCourseEnglish202(Staff profBlack, Department humanities) {
		return new Course("English 202", 3, profBlack, humanities);
	}

	private Course createCourseEnglish101(Staff profBlack, Department humanities) {
		return new Course("English 101", 3, profBlack, humanities);
	}

}