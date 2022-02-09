package com.truper.spring.core.practicaD.propertysource.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
// Define property sources
@PropertySources({ 
	@PropertySource("classpath:/spring/practicaD/app.properties"),
	@PropertySource("classpath:/spring/practicaD/app2.properties") 
})
public class PropertySourceConfig {

}
