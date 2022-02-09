package com.truper.spring.core.practica18.jsr330.bean;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.truper.spring.core.practica18.jsr330.bean.api.IDirector;
import com.truper.spring.core.practica18.jsr330.qualifiers.SecretaryGeneralEmployeeQualifier;

import lombok.Data;

@Data
// Anotar como bean
@Named
@Singleton
public class Corporation {

	// Inyectar
	@Inject
	@org.springframework.beans.factory.annotation.Qualifier("generalDirectorBean")
	private IDirector generalDirector;

	// Inyectar
	@Inject
	private IDirector itDirector;

	// Inyectar
	@Inject
	@SecretaryGeneralEmployeeQualifier
	private Optional<Secretary> generalSecretary; // esta vacante (tiene que ser empty)

	// Inyectar
	@Inject
	private Secretary secretaryAssistant;

	// Inyectar
	@Inject
	private Manager manager;
}
