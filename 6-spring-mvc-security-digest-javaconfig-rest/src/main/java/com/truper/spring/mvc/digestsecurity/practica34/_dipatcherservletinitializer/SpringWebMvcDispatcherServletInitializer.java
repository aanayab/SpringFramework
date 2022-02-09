package com.truper.spring.mvc.digestsecurity.practica34._dipatcherservletinitializer;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.truper.spring.mvc.digestsecurity.practica34._configuration.RootContextConfiguration;
import com.truper.spring.mvc.digestsecurity.practica34._configuration.SecurityContextConfiguration;
import com.truper.spring.mvc.digestsecurity.practica34._configuration.ServletContextConfiguration;

//Extiende de AbstractAnnotationConfigDispatcherServletInitializer
public class SpringWebMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// Sobre escribe el metodo getRootConfigClasses
	@Override
	protected Class<?>[] getRootConfigClasses() { // Clases @Configuration para configurar el RootApplicationContext (ApplicationContext)
		return new Class[] { RootContextConfiguration.class, SecurityContextConfiguration.class };
	}

	// Sobre escribe el metodo getServletConfigClasses
	@Override
	protected Class<?>[] getServletConfigClasses() { // Clases @Configuration para configurar el WebApplicationContext
		return new Class[] { ServletContextConfiguration.class };
	}

	// Sobre escribe el metodo getServletMappings
	@Override
	protected String[] getServletMappings() { // Definir el mapeo de los requests que atendera el DispatcherServlet
		return new String[] { "/" };
	}

}