package com.truper.spring.tx.pratica26.springtx.service.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.truper.spring.tx.pratica26.springtx.domain.BusinessObject;
import com.truper.spring.tx.pratica26.springtx.service.api.ITransactionalService;
import com.truper.spring.tx.util.Color;
import com.truper.spring.tx.util.bean.api.IColorWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
// Habilitar Trasnactional con aislamiento read commited y propagacion required
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class TransactionalService implements ITransactionalService {

	@Autowired
	private IColorWriter colorWriter;

	@Override
	// Habilitar Trasnactional read only
	@Transactional(readOnly = true)
	public BusinessObject getBusinessObject(Long id) {
		log.info("{}", colorWriter.getColoredMessage(Color.CYAN,
				"inside getBusinessObject"));
		BusinessObject bo = BusinessObject.builder().id(id).data("some data")
				.build();
		return bo;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRES_NEW)
	public void insertBusinessObject(BusinessObject businessObject) {
		log.info("{}", colorWriter.getColoredMessage(Color.CYAN,
				"inside insertBusinessObject"));
		throw new UnsupportedOperationException(
				"unsupported insertBusinessObject");
	}

	@Override
	public void updateBusinessObject(BusinessObject businessObject) {
		log.info("{}", colorWriter.getColoredMessage(Color.CYAN,
				"inside updateBusinessObject"));
		throw new UnsupportedOperationException(
				"unsupported updateBusinessObject");
	}

	@Override
	public void deleteBusinessObject(Long id) {
		log.info("{}", colorWriter.getColoredMessage(Color.CYAN,
				"inside deleteBusinessObject"));
		log.info("BusinessObject id: {} deleted", id);
	}

}
