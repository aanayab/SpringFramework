package com.truper.spring.core.practica5.test.scopes;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.truper.spring.core.practica5.scopes.bean.Persona;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomScopesSpringTest {

	private static ConfigurableApplicationContext applicationContext;

	@BeforeClass
	public static void beforeClass() {
		// Instanciar ApplicationContext
		String ruta = "classpath:/spring/practica5/scopes-application-context.xml";
		applicationContext = new ClassPathXmlApplicationContext(ruta);
	}

	@AfterClass
	public static void afterClass() {
		// cerrar ApplicationContext
		applicationContext.close();
	}

	@Test
	public void customScopesSpringTest() {

		log.info("scopeSingletonSpringTest -------------------");

		// Implementar
		for (int i = 0; i < 19; i++) {
			Persona persona = applicationContext.getBean("personaCustomScopeBean", Persona.class);

			Assert.assertNotNull(persona);
			Assert.assertEquals("Ivan", persona.getNombre());

			log.info("persona: {} {} {}", persona, persona.hashCode(), System.identityHashCode(persona));
		}

		Persona persona = applicationContext.getBean("personaCustomScopeBean", Persona.class);

		log.info("persona: {} {} {}", persona, persona.hashCode(), System.identityHashCode(persona));

		persona = applicationContext.getBean("personaCustomScopeBean", Persona.class);

		log.info("persona: {} {} {}", persona, persona.hashCode(), System.identityHashCode(persona));
	}

}
