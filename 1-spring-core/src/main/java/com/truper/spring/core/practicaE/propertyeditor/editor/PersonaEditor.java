package com.truper.spring.core.practicaE.propertyeditor.editor;

import java.beans.PropertyEditorSupport;

import com.truper.spring.core.practicaE.propertyeditor.bean.Persona;

// Extiende PropertyEditorSupport
public class PersonaEditor extends PropertyEditorSupport {

	@Override
	public String getAsText() {
		Persona persona = (Persona) this.getValue();

		return persona == null ? ""
				: new StringBuilder(persona.getNombre())
				.append(":").append(persona.getEdad())
				.append(":").append(persona.getSexo()).toString();
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {

		String[] values = text.split(":");
		Persona p = Persona.builder()
				.nombre(values[0])
				.edad(Integer.valueOf(values[1]))
				.sexo(values[2])
				.build();

		this.setValue(p);
	}
}