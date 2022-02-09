package com.truper.spring.data.practicaH.test.jpa;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.truper.spring.data.practicaH.jpa._config.SpringDataJpaConfiguration;
import com.truper.spring.data.practicaH.jpa.entity.Department;
import com.truper.spring.data.practicaH.jpa.repositories.DepartmentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = SpringDataJpaConfiguration.class)
public class DepartmentRepositoryTest {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Test
	public void simpleDepartmentCrudExample() {

		log.info("simpleDepartmentCrudExample test starts =======================================================");

		// guarda el departamento "Humanities"
		departmentRepository.save(new Department("Humanities"));
		
		// ejecuta flush de la sesion 
		departmentRepository.flush();
		
		// guarda el departamento "Fine Arts" y haz flush en una sola operacion
		departmentRepository.saveAndFlush(new Department("Fine Arts"));

		// guarda el departamento "Social Science"
		departmentRepository.save(new Department("Social Science"));

		System.out.println("\n*************3 Departments*************");
		List<Department> departments = departmentRepository.findAll(); // busca todos los departamentos

		Assert.assertEquals(3, departments.size());

		departments.forEach(System.out::println);

		List<Department> reloadedDepartments = departmentRepository.findAll(); // busca todos los departamentos otra vez

		Assert.assertEquals(3, reloadedDepartments.size());

		// lista de departamentos a eliminar
		List<Department> listToDelete = reloadedDepartments.subList(0, 1);

		Assert.assertEquals(1, listToDelete.size());
		Assert.assertEquals("Humanities", listToDelete.get(0).getName());

		// elimina en batch los departamentos listToDelete
		departmentRepository.deleteAll(listToDelete);

		System.out.println("\n*************1 Less Departments*************");
		List<Department> _1_lessDepartments = departmentRepository.findAll(); // busca todos los departamentos otra vez

		Assert.assertEquals(2, _1_lessDepartments.size());

		_1_lessDepartments.forEach(System.out::println);

		// borra todos los departamentos en batch
		departmentRepository.deleteAllInBatch();

		System.out.println("\n*************Zero Departments*************");
		List<Department> zeroDepartments = departmentRepository.findAll(); // busca todos los departamentos otra vez

		Assert.assertEquals(0, zeroDepartments.size());

		zeroDepartments.forEach(System.out::println);

		log.info("simpleDepartmentCrudExample test ends =======================================================");
	}

	@Before
	@After
	public void banner() {
		System.out.println(
				"\n\n-------------------------------------------------" + "-------------------------------------\n");
	}

}