package com.truper.spring.core.practica5.test.scopes;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.truper.spring.core.practica5.scopes.bean.Persona;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadScopesSpringTest {

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
	public void threadScopeSpringTest() {

		log.info("threadScopeSpringTest -------------------");

		// Implementar

		for (int i = 0; i < 10; i++) {
			Persona persona = applicationContext.getBean("personaThreadScopeBean", Persona.class);

			Assert.assertNotNull(persona);
			Assert.assertEquals("Ivan", persona.getNombre());

			String tName = Thread.currentThread().getName();
			log.info("[" + tName + "] persona: {} {} {}", persona, persona.hashCode(),
					System.identityHashCode(persona));
		}
	}

	@Test
	@SneakyThrows
	public void threadScopeSpringTest2() {

		log.info("threadScopeSpringTest2 -------------------");

		// Implementar

		for (int i = 0; i < 10; i++) {

			Thread th = new Thread(new Runnable() {

				@Override
				public void run() {

					for (int j = 0; j < 10; j++) {
						Persona persona = applicationContext.getBean("personaThreadScopeBean", Persona.class);

						Assert.assertNotNull(persona);
						Assert.assertEquals("Ivan", persona.getNombre());

						String tName = Thread.currentThread().getName();
						log.info("[" + tName + "] persona: {} {} {}", persona, persona.hashCode(),
								System.identityHashCode(persona));
					}
				}
			});

			th.start();

		}

		Thread.sleep(5000);
	}

}
