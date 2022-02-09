package com.truper.spring.web.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		// Aqui se crea el webApplicationContext
		WebApplicationContext webApplicationContext = getWebApplicationContext();
		
		// Aqui se registra el ContextLoaderListener creando el RootApplicationContext
		servletContext.addListener(new ContextLoaderListener(getRootApplicationContext()));
		
		// Aqui creo el DispatcherServlet y le paso el webApplicationContext
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
				"dispatcherServlet", new DispatcherServlet(webApplicationContext));
		
		// configuro el DispatcherServlet
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

	private WebApplicationContext getRootApplicationContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation("com.truper.spring.web.root.config");
		return context;
	}

	private WebApplicationContext getWebApplicationContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation("com.truper.spring.web.mvc.config");
		return context;
	}

}