package com.truper.spring.tx.practica26.test.springtx.parte2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.truper.spring.tx.pratica26.springtx.config.TransactionalAnnotationAppConfiguration;
import com.truper.spring.tx.pratica26.springtx.domain.BusinessObject;
import com.truper.spring.tx.pratica26.springtx.service.api.ITransactionalService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TransactionalAnnotationAppConfiguration.class)
public class DeclarativeAnnotationsTransactionManagerTest {

	@Autowired
	private ITransactionalService transactionalService;

	@Before
	public void setUp() {
		Assert.assertNotNull(transactionalService);
	}

	@Test
	public void declarativeXMLTransactionManagerTest() {
		log.info("declarativeXMLTransactionManagerTest -------------------");

		log.info("calling: transactionalService.getBusinessObject(5L)");
		BusinessObject bo = null;
		try {
			bo = transactionalService.getBusinessObject(5L);
		} catch (Exception ex) {
			log.info(
					"transactionalService.getBusinessObject(5L) throws Exception {}",
					ex.getMessage());
		}
		log.info("getBusinessObject(5L): {}", bo);
	}
	
	@Test
	public void declarativeXMLTransactionManagerTest2() {
		log.info("declarativeXMLTransactionManagerTest2 -------------------");

		log.info("calling: transactionalService.insertBusinessObject(null)");
		try {
			transactionalService.insertBusinessObject(null);
		} catch (Exception ex) {
			log.info(
					"transactionalService.insertBusinessObject(null) throws Exception {}",
					ex.getMessage());
		}
	}
	
	@Test
	public void declarativeXMLTransactionManagerTest3() {
		log.info("declarativeXMLTransactionManagerTest3 -------------------");

		log.info("calling: transactionalService.updateBusinessObject(bo)");
		
		BusinessObject bo = null;
		
		try {
			transactionalService.updateBusinessObject(bo);
		} catch (Exception ex) {
			log.info(
					"transactionalService.updateBusinessObject(bo) throws Exception {}",
					ex.getMessage());
		}
	}
	
	@Test
	public void declarativeXMLTransactionManagerTest4() {
		log.info("declarativeXMLTransactionManagerTest4 -------------------");

		log.info("calling: transactionalService.deleteBusinessObject(5L)");
		try {
			transactionalService.deleteBusinessObject(5L);
		} catch (Exception ex) {
			log.info(
					"transactionalService.deleteBusinessObject(5L) throws Exception {}",
					ex.getMessage());
		}

		log.info("\n----------------------------------------------");
	}
}
