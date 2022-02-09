package com.truper.spring.data.practicaH.test.jpa;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.truper.spring.data.practicaH.jpa._config.SpringDataJpaConfiguration;
import com.truper.spring.data.practicaH.jpa.entity.Staff;
import com.truper.spring.data.practicaH.jpa.repositories.StaffRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ActiveProfiles("init-database")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = SpringDataJpaConfiguration.class)
public class StaffRepositoryTest {

	@Autowired
	private StaffRepository staffRepository;

	@Test
	public void queryStaffCrudExample() {

		log.info("queryStaffCrudExample test starts =======================================================");

		System.out.println("\n*************@Query Staff Example*************");
		List<Staff> staff = staffRepository.selectStaffByFullName("Moore", "Allison"); // busca un Staff por nombre completo (JPQL)

		Assert.assertEquals(1, staff.size());

		staff.forEach(System.out::println);
		
		System.out.println();
		
		System.out.println("\n*************Native @Query implementation Staff Example*************");
		List<Staff> staff2 = staffRepository.selectNativeStaffByFullName("Moore", "Allison"); // busca un Staff por nombre completo (SQL nativo)

		Assert.assertEquals(1, staff2.size());

		staff2.forEach(System.out::println);
		
		System.out.println();
		
		System.out.println("\n*************Custom query implementation Staff Example*************");
		// busca un Staff por nombre completo usando el metodo customizado buscaStaffPorNombreCompleto
		List<Staff> staff3 = staffRepository.buscaStaffPorNombreCompleto("Moore", "Allison");

		Assert.assertEquals(1, staff3.size());

		staff3.forEach(System.out::println);
		
		// busca un Staff por nombre completo usando el metodo customizado buscaStaffPorNombreCompletoUsandoConsultaNativa
		List<Staff> staff4 = staffRepository.buscaStaffPorNombreCompletoUsandoConsultaNativa("Allison", "Moore"); 

		Assert.assertEquals(1, staff4.size());

		staff4.forEach(System.out::println);

		log.info("queryStaffCrudExample test ends =======================================================");
	}

}