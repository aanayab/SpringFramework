package com.truper.spring.tx.programatictx.service.api;

import com.truper.spring.tx.pratica26.springtx.domain.BusinessObject;

public interface IProgramaticTransactionalService {

	BusinessObject returningObjectProgramaticTransactionalMethod(Long id);

	void withoutReturningObjectProgramaticTransactionalMethod(BusinessObject businessObject);
}
