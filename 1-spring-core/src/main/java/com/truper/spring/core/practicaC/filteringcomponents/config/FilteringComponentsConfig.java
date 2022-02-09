package com.truper.spring.core.practicaC.filteringcomponents.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;

import com.truper.spring.core.practicaC.filteringcomponents.bean.Biker;

// Anotar como clase de Configuracion
@Configuration

// Component scan base = "com.truper.spring.core.practicaC"
@ComponentScan(basePackages = "com.truper.spring.core.practicaC", excludeFilters = {
		
		// excluir anotaciones Repository y
		@Filter(type = FilterType.ANNOTATION, classes = Repository.class),
		
		// excluir tipos asignables a Biker.class
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = Biker.class) 
})
public class FilteringComponentsConfig {

}
