package com.truper.spring.tx.pratica26.springtx.service.api;

import com.truper.spring.tx.pratica26.springtx.domain.BusinessObject;

public interface ITransactionalService {

	BusinessObject getBusinessObject(Long id);

	void insertBusinessObject(BusinessObject businessObject);

	void updateBusinessObject(BusinessObject businessObject);

	void deleteBusinessObject(Long id);
}
