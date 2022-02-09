package com.truper.spring.data.practicaH.jpa._config;

import java.time.LocalDate;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.truper.spring.data.practicaH.jpa.entity.Course;
import com.truper.spring.data.practicaH.jpa.entity.Department;
import com.truper.spring.data.practicaH.jpa.entity.Person;
import com.truper.spring.data.practicaH.jpa.entity.Staff;
import com.truper.spring.data.practicaH.jpa.entity.Student;
import com.truper.spring.data.practicaH.jpa.repositories.CourseRepository;
import com.truper.spring.data.practicaH.jpa.repositories.DepartmentRepository;
import com.truper.spring.data.practicaH.jpa.repositories.StaffRepository;
import com.truper.spring.data.practicaH.jpa.repositories.StudentRepository;

// Clase de configuración @Configuration
@Configuration
// Component Scan de paquete base Spring data practicaH JPA
@ComponentScan(basePackages = "com.truper.spring.data.practicaH.jpa")

// Habilitar repositorios Spring Data JPA
@EnableJpaRepositories(basePackages = "com.truper.spring.data.practicaH.jpa.repositories")

// Opcional habilitar manejo transaccional
@EnableTransactionManagement
public class SpringDataJpaConfiguration {

	// Definir Bean LocalContainerEntityManagerFactoryBean
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
			JpaVendorAdapter jpaVendorAdapter) {

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

		em.setDataSource(dataSource);
		em.setPackagesToScan("com.truper.spring.data.practicaH.jpa.entity");
		em.setJpaVendorAdapter(jpaVendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

	// Analiza las entidades definidas

	// Propiedades Hibernate
	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		// properties.setProperty("hibernate.show_sql", "true");
		// properties.setProperty("hibernate.format_sql", "true");
		// properties.setProperty("hibernate.use_sql_comments", "true");

		// Dialecto para MySQL org.hibernate.dialect.MySQL5Dialect
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

		return properties;
	}

	// Opcional, definir Bean PlatformTransactionManager del tipo
	// JpaTransactionManager
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

	// DAO Support, Translacion de excepciones
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	// Define Bean DataSource en memoria mediante EmbeddedDatabaseBuilder
	@Bean
	@Profile("init-database")
	public DataSource dataSourceInitDataBase() {
		return new EmbeddedDatabaseBuilder()
				.generateUniqueName(true)
				.setType(EmbeddedDatabaseType.H2)
				.build();
	}
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.generateUniqueName(true)
				.setType(EmbeddedDatabaseType.H2)
				.addScript("db/jpa/schema.sql")
				.addScript("db/jpa/data.sql")
				.build();
	}

	// Analiza
	@Bean
	@Profile("init-database")
	public ApplicationListener<ContextRefreshedEvent> startupBean(StudentRepository studentRepository,
			StaffRepository staffRepository, CourseRepository courseRepository,
			DepartmentRepository departmentRepository) {

		return new ApplicationListener<ContextRefreshedEvent>() {

			@SuppressWarnings("unused")
			@Override
			public void onApplicationEvent(ContextRefreshedEvent event) {

				// Students
				boolean fullTime = true;

				// almacena los estudiantes
				Student janeDoe = createStudentJaneDoe(fullTime);
				studentRepository.save(janeDoe);
				
				studentRepository.save(createStudentYulianaDoe(fullTime));
				studentRepository.save(createStudentKarlaJimenez(fullTime));
				studentRepository.save(createStudentJohnDoe(fullTime));
				studentRepository.save(createStudentMikeSmith(fullTime));
				studentRepository.save(createStudentAllyKim(fullTime));

				// Staff
				Staff deanJones = staffRepository.save(createStaffJohnJones()); // almacena al StaffJohnJones
				Staff deanMartin = staffRepository.save(createStaffMatthewMartin()); // almacena al StaffMatthewMartin
				Staff profBrown = staffRepository.save(createStaffJamesBrown()); // almacena al StaffJamesBrown
				Staff profMiller = staffRepository.save(createStaffJudyMiller()); // almacena al StaffJudyMiller
				Staff profDavis = staffRepository.save(createStaffJamesDavis()); // almacena al StaffJamesDavis
				Staff profMoore = staffRepository.save(createStaffAllisonMoore()); // almacena al StaffAllisonMoore
				Staff profThomas = staffRepository.save(createStaffTomThomas()); // almacena al StaffTomThomas
				Staff profGreen = staffRepository.save(createStaffGrahamGreen()); // almacena al StaffGrahamGreen
				Staff profWhite = staffRepository.save(createStaffWhitneyWhite()); // almacena al StaffWhitneyWhite
				Staff profBlack = staffRepository.save(createStaffJackBlack()); // almacena al StaffJackBlack
				Staff profKing = staffRepository.save(createStaffQueenKing()); // almacena al StaffQueenKing

				// Departments
				Department humanities = departmentRepository.save(createDepartmentHumanities(deanJones)); // almacena al DepartmentHumanities con deanJones como jefe de depto
				Department naturalSciences = departmentRepository.save(createDepartmentNaturalSciences(deanMartin)); // almacena al DepartmentNaturalSciences como deanMartin como jefe de
													// depto
				Department socialSciences = departmentRepository.save(createDepartmentSocialSciences(deanJones)); // almacena al DepartmentSocialSciencescomo deanJones como jefe de
													// depto

				// Humanities Courses
				Course english101 = courseRepository.save(createCourseEnglish101(profBlack, humanities)); // almacena al CourseEnglish101 con profBlack como profesor del depto
											// humanities
				Course english202 = courseRepository.save(createCourseEnglish202(profBlack, humanities)); // almacena al CourseEnglish202 con profBlack como profesor del depto
											// humanities

				// agrega el curso english101 como prerequisito del curso english202
				courseRepository.save(english202.addPrerequisite(english101));

				Course english201 = courseRepository.save(createCourseEnglish201(profBrown, humanities)); // almacena al CourseEnglish201 con profBrown como profesor del depto
											// humanities

				// agrega el curso english101 como prerequisito del curso english201
				courseRepository.save(english201.addPrerequisite(english101));

				// Natural Science Courses
				Course chemistry = courseRepository.save(createCourseChemistry(profDavis, naturalSciences)); // almacena al CourseChemistry con profDavis como profesor del depto
											// naturalSciences
				Course physics = courseRepository.save(createCoursePhysics(profDavis, naturalSciences)); // almacena al CoursePhysics con profDavis como profesor del depto
										// naturalSciences

				// agrega el curso chemistry como prerequisito del curso physcics
				courseRepository.save(physics.addPrerequisite(chemistry));

				Course cProgramming = courseRepository.save(createCourseCProgramming(profMoore, naturalSciences)); // almacena al CourseCProgramming con profMoore como profesor del depto
											// naturalSciences
				Course jProgramming = courseRepository.save(createCourseJavaProgramming(profMoore, naturalSciences)); // almacena al CourseJavaProgramming con profMoore como profesor del depto
											// naturalSciences

				// Social Science Courses
				Course history101 = courseRepository.save(createCourseHistory101(profMiller, socialSciences)); // almacena al CourseHistory101 con profMiller como profesor del depto
											// socialSciences
				Course anthro = courseRepository.save(createCourseAnthropology(profKing, socialSciences)); // almacena al CourseAnthropology con profKing como profesor del depto
										// socialSciences

				// agrega el curso history101 como prerequisito del curso anthropology
				courseRepository.save(anthro.addPrerequisite(history101));

				Course sociology = courseRepository.save(createCourseSociology(profKing, socialSciences)); // almacena al CourseSociology con profKing como profesor del depto
											// socialSciences

				// agrega el curso history101 como prerequisito del curso sociology
				courseRepository.save(sociology.addPrerequisite(history101));

				Course psych = courseRepository.save(createCoursePsychology(profWhite, socialSciences)); // almacena al CoursePsychology con profWhite como profesor del depto
										// socialSciences

				// agrega el curso history101 y el curso english101 como prerequisitos del curso
				// psychology
				courseRepository.save(psych.addPrerequisite(history101).addPrerequisite(english101));

			}

			private Course createCoursePsychology(Staff profWhite, Department socialSciences) {
				return new Course("Psychology", 3, profWhite, socialSciences);
			}

			private Course createCourseSociology(Staff profKing, Department socialSciences) {
				return new Course("Sociology", 3, profKing, socialSciences);
			}

			private Course createCourseAnthropology(Staff profKing, Department socialSciences) {
				return new Course("Anthropology ", 3, profKing, socialSciences);
			}

			private Course createCourseHistory101(Staff profMiller, Department socialSciences) {
				return new Course("History 101", 3, profMiller, socialSciences);
			}

			private Course createCourseJavaProgramming(Staff profMoore, Department naturalSciences) {
				return new Course("Java Programming", 3, profMoore, naturalSciences);
			}

			private Course createCourseCProgramming(Staff profMoore, Department naturalSciences) {
				return new Course("C Programming", 3, profMoore, naturalSciences);
			}

			private Course createCoursePhysics(Staff profDavis, Department naturalSciences) {
				return new Course("Physics", 3, profDavis, naturalSciences);
			}

			private Course createCourseChemistry(Staff profDavis, Department naturalSciences) {
				return new Course("Chemistry", 3, profDavis, naturalSciences);
			}

			private Course createCourseEnglish201(Staff profBrown, Department humanities) {
				return new Course("English 201", 3, profBrown, humanities);
			}

			private Course createCourseEnglish202(Staff profBlack, Department humanities) {
				return new Course("English 202", 3, profBlack, humanities);
			}

			private Course createCourseEnglish101(Staff profBlack, Department humanities) {
				return new Course("English 101", 3, profBlack, humanities);
			}

			private Department createDepartmentSocialSciences(Staff deanJones) {
				return new Department("Social Sciences", deanJones);
			}

			private Department createDepartmentNaturalSciences(Staff deanMartin) {
				return new Department("Natural Sciences", deanMartin);
			}

			private Department createDepartmentHumanities(Staff deanJones) {
				return new Department("Humanities", deanJones);
			}

			private Staff createStaffQueenKing() {
				return new Staff(new Person("Queen", "King"));
			}

			private Staff createStaffJackBlack() {
				return new Staff(new Person("Jack", "Black"));
			}

			private Staff createStaffWhitneyWhite() {
				return new Staff(new Person("Whitney", "White"));
			}

			private Staff createStaffGrahamGreen() {
				return new Staff(new Person("Graham", "Green"));
			}

			private Staff createStaffTomThomas() {
				return new Staff(new Person("Tom", "Thomas"));
			}

			private Staff createStaffAllisonMoore() {
				return new Staff(new Person("Allison", "Moore"));
			}

			private Staff createStaffJamesDavis() {
				return new Staff(new Person("James", "Davis"));
			}

			private Staff createStaffJudyMiller() {
				return new Staff(new Person("Judy", "Miller"));
			}

			private Staff createStaffJamesBrown() {
				return new Staff(new Person("James", "Brown"));
			}

			private Staff createStaffMatthewMartin() {
				return new Staff(new Person("Matthew", "Martin"));
			}

			private Staff createStaffJohnJones() {
				return new Staff(new Person("John", "Jones"));
			}

			private Student createStudentAllyKim(boolean fullTime) {
				return new Student(new Person("ally", "kim"), !fullTime, LocalDate.of(1987, 07, 22));
			}

			private Student createStudentMikeSmith(boolean fullTime) {
				return new Student(new Person("mike", "smith"), fullTime, LocalDate.of(1987, 12, 06));
			}

			private Student createStudentJohnDoe(boolean fullTime) {
				return new Student(new Person("john", "doe"), fullTime, LocalDate.of(1987, 06, 23));
			}

			private Student createStudentKarlaJimenez(boolean fullTime) {
				return new Student(new Person("karla", "jimenez"), fullTime, LocalDate.of(1990, 01, 04));
			}

			private Student createStudentYulianaDoe(boolean fullTime) {
				return new Student(new Person("yuliana", "doe"), fullTime, LocalDate.of(1988, 02, 13));
			}

			private Student createStudentJaneDoe(boolean fullTime) {
				return new Student(new Person("jane", "doe"), fullTime, LocalDate.of(1988, 02, 13));
			}
		};
	}
}
