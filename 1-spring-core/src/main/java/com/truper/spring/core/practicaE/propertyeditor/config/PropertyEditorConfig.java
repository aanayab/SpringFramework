package com.truper.spring.core.practicaE.propertyeditor.config;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.truper.spring.core.practicaE.propertyeditor.bean.CreditCard;
import com.truper.spring.core.practicaE.propertyeditor.bean.Customer;
import com.truper.spring.core.practicaE.propertyeditor.bean.Persona;
import com.truper.spring.core.practicaE.propertyeditor.editor.CreditCardEditor;
import com.truper.spring.core.practicaE.propertyeditor.editor.PersonaEditor;

@Configuration
public class PropertyEditorConfig {

	// Define el Bean CustomEditorConfigurer
	@Bean
	public static CustomEditorConfigurer customEditorConfigurer() {

		CustomEditorConfigurer cec = new CustomEditorConfigurer();

		Map<Class<?>, Class<? extends PropertyEditor>> customEditors = new HashMap<>();

		// Implementar lo que falta
		customEditors.put(CreditCard.class, CreditCardEditor.class);
		customEditors.put(Persona.class, PersonaEditor.class);
		
		cec.setCustomEditors(customEditors);

		return cec;
	}

	// Define el Bean Customer
	@Bean
	public Customer customerIvan(@Value("2222-2233-3333-3334") CreditCard cc) {
		return Customer.builder()
				.name("Ivan Garcia")
				.creditCard(cc).build();
	}
}
