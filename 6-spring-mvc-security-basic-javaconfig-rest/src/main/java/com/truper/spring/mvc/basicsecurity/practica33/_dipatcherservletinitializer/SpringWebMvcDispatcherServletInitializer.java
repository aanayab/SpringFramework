package com.truper.spring.mvc.basicsecurity.practica33._dipatcherservletinitializer;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.truper.spring.mvc.basicsecurity.practica33._configuration.RootContextConfiguration;
import com.truper.spring.mvc.basicsecurity.practica33._configuration.SecurityContextConfiguration;
import com.truper.spring.mvc.basicsecurity.practica33._configuration.ServletContextConfiguration;

// Extiende de AbstractAnnotationConfigDispatcherServletInitializer
// Es la que configura lo que poniamos en el web.xml correspondiente a Spring MVC
// 1. Registrar el Dispatcher Servlet
// 2. Registrar el ContextLoaderListener para registrar el RootApplicationContext
// 3. Registrar el WebApplicationContext asociado al Dispatcher Servlet
// 4. Configurar el url-mapping del Dispatcher Servlet.
public class SpringWebMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// Sobre escribe el metodo getRootConfigClasses
	// Se setean las clases de configuracion del RootApplicationContext (Beans de aplicacion (no web, no @Controllers), Seguridad)
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { RootContextConfiguration.class, SecurityContextConfiguration.class};
	}

	// Sobre escribe el metodo getServletConfigClasses
	// Se setean las clases de configuracion del WebApplicationContext
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { ServletContextConfiguration.class };
	}

	// Sobre escribe el metodo getServletMappings
	// Se setea el url-mapping del DispatcherServlet
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/" };
	}

}