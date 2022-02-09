package com.truper.spring.core.practicaE.propertyeditor.config;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import com.truper.spring.core.practicaE.propertyeditor.bean.CreditCard;
import com.truper.spring.core.practicaE.propertyeditor.editor.CreditCardEditor;

// Implementa PropertyEditorRegistrar 
public class CreditCardPropertyEditorRegistrar implements PropertyEditorRegistrar {

	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		registry.registerCustomEditor(CreditCard.class, new CreditCardEditor());
	}

}