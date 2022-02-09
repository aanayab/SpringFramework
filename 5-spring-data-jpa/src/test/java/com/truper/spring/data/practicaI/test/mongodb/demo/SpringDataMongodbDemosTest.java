package com.truper.spring.data.practicaI.test.mongodb.demo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.MappingException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.truper.spring.data.practicaI.mongodb._config.SpringDataMongodbConfiguration;
import com.truper.spring.data.practicaI.mongodb.document.Staff;
import com.truper.spring.data.practicaI.mongodb.repositories.DepartmentRepository;
import com.truper.spring.data.practicaI.mongodb.repositories.StaffRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ActiveProfiles({ "init-database", "sin-mongodbfactory" })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringDataMongodbConfiguration.class)
public class SpringDataMongodbDemosTest {

	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Before
	public void setup() {
		Assert.assertNotNull(staffRepository);
		Assert.assertNotNull(departmentRepository);
		Assert.assertNotNull(mongoTemplate);
	}
	
	@After
	public void afterClass() {
		mongoTemplate.getDb().drop();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void mongoQueryMethods() {

		log.info("mongoQueryMethods test starts =======================================================");

		// ***************Staff query methods***************

		// Paging and Sorting Queries
		System.out.println("\nFind all staff members, sort alphabetically by first name and last name");
		
		// Define Sort por "member.lastName", "member.firstName" Ascendente
		Sort sortByLastName = new Sort(Sort.Direction.ASC, "member.firstName", "member.lastName");
		
		// Consulta todo el Staff ordenado por Sort e imprime los resultados
		staffRepository.findAll(sortByLastName).forEach(staff -> System.out.println(staff));

		int total3StaffMembers = Long.valueOf(staffRepository.count()).intValue(); // Cuenta cuantos Staff existen en el repositorio
		String[] th = { "st", "nd", "rd", "th" };

		// Implementa paginacon
		for (int i = 0; i < Math.ceil(total3StaffMembers / 3D); i++) {
			
			System.out.println("\nFind " + (i + 1) + th[(i < 3) ? i : 3] + " 3 Staff members of " + total3StaffMembers
					+ ", sort by alphabetically by last name using PageRequest");

			// consulta todos los Staff de forma paginada (tamanio de pagina de 3 elementos) y ordenalos conforme a la variable Sort "sortByLastName" definida.
			Page<Staff> staff = staffRepository.findAll(PageRequest.of(i, 3, sortByLastName)); 
			
			staff.forEach(System.out::println);
		}

		System.out.println("\nFind first 3 Staff members, sort alphabetically by last name using PageRequest");
		// busca la primera pagina de tamanio 3 de todos los Staff en el repositorio, utiliza la variable Sort "sortByLastName" para ordenarlos.
		Page<Staff> members = staffRepository.findAll(PageRequest.of(0, 3, sortByLastName)); 
		members.forEach(System.out::println);

		// Property Expression
		System.out.println("\nFind all staff members with last name King");
		// busca los Staff con "member.lastName" igual a "King"
		staffRepository.findByMemberLastName("King").forEach(System.out::println);

		// @Query with JSON query string
		// "{ 'member.firstName' : ?0 }"
		System.out.println("\nFind all staff members with first name John");
		// busca los Staff por "firstName" igual a "John", utiliza @Query para definir una consulta nativa MongoDB.
		staffRepository.findByFirstName("John").forEach(System.out::println);
		
		// ***************Department query methods***************

		// Sorting example, MongoRepository extends PagingAndSortingRepository
		System.out.println("\nFind all Departments, sort alphabetically by name Descending");
		// busca todos los departamentos, ordenalos definiendo un Sort por "name" descendientemente.
		Sort sortByName = new Sort(Sort.Direction.DESC, "name");
		departmentRepository.findAll(sortByName).forEach(System.out::println);

		// Property Expression
		System.out.println("\nFind Department with the exact name 'Humanities' \n"
				+ departmentRepository.findByName("Humanities")); // busca el departamento por "name" igual a "Humanities".

		// @Query with JSON query string that accepts regular expression as a parameter
		// { 'name' : { $regex: ?0 } }
		// Any department name that ends in sciences where 's' is case insensitive
		System.out.println("\nFind all Departments with name ending in Sciences");
		// busca todos los Department donde el "name" termine con "sciences" (case-insensitive), usa @Query methods
		departmentRepository.findDepartmentByNamePattern(".[sS]ciences").forEach(System.out::println);
		System.out.println();
		// busca todos los Department donde el "name" termine con "sciences" (case-insensitive), usa derived queries
		departmentRepository.findDepartmentByNameContainingIgnoreCase("sciences").forEach(System.out::println);
		

		try {
			// Invalid Method, will fail at runtime
			System.out.println("\nInvalid Method, cannot cross DBRef's in queries");
			// Intenta buscar los Department por Chair Member LastName igual a "Jones", utiliza derived queries.
			departmentRepository.findDepartmentByChairMemberFirstName("John").forEach(System.out::println);
			

			Assert.fail("Should fail at this point");

		} catch (MappingException ex) {
			System.out.println("exception: " + ex.getMessage());
		}
		
		// Logra hacer la consulta de los Department por Chair Member LastName igual a "Jones", utiliza custom repositories.
		departmentRepository.findDepartmentByChairFirstName("John").forEach(System.out::println);

		log.info("mongoQueryMethods test ends =======================================================");
	}

	@Before
	@After
	public void printBanner() {
		System.out.println("*************************************************************************************");
	}

}
