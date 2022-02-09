package com.truper.spring.data.practicaJ.jdbc._config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jdbc.repository.config.JdbcConfiguration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mongodb.annotations.Beta;
import com.truper.spring.data.practicaJ.jdbc.entity.Chair;
import com.truper.spring.data.practicaJ.jdbc.entity.Department;
import com.truper.spring.data.practicaJ.jdbc.repositories.DepartmentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//Clase de configuración @Configuration
@Configuration
//Component Scan de paquete base Spring data practicaJ JDBC
@ComponentScan(basePackages = "com.truper.spring.data.practicaJ.jdbc")

//Habilitar repositorios Spring Data JDBC
@EnableJdbcRepositories(basePackages = "com.truper.spring.data.practicaJ.jdbc.repositories")

//Opcional habilitar manejo transaccional
@EnableTransactionManagement

//Extiende de JdbcConfiguration, la clase de configuracion debe configurar otros beans de infraestructura
public class SpringDataJdbcConfiguration extends JdbcConfiguration {

	// Define Bean DataSource
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.generateUniqueName(true)
				.setType(EmbeddedDatabaseType.H2)
				.addScript("db/jdbc/schema.sql")
				.addScript("db/jdbc/data.sql")
				.build();
	}
	
	// Define Bean NamedParameterJdbcTemplate (Spring Data JDBC unicamente soprota queries con parametros nombrados, NO por posicion)
	@Bean
	public NamedParameterJdbcOperations namedParameterJdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	// Opcional, definir Bean PlatformTransactionManager del tipo DataSourceTransactionManager
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Autowired
	private DepartmentRepository departmentRepository;

	@Bean
	@Profile("init-database")
	public ApplicationListener<ContextRefreshedEvent> startupBean() {

		return new ApplicationListener<ContextRefreshedEvent>() {

			@SuppressWarnings("unused")
			@Override
			public void onApplicationEvent(ContextRefreshedEvent event) {

				// Chairs
				Chair deanJones = new Chair("Dean Jones"); // crea nuevo objeto Chair "Dean Jones"
				Chair deanMartin = new Chair("Dean Martin"); // crea nuevo objeto Chair "Dean Martin"
				//Chair deanCain = new Chair("Dean Cain"); // crea nuevo objeto Chair "Dean Cain"

				// Departments
				log.info("SQL to Create Humanities");
				// almacena Departamento "Humanities" con deanJones como jefe de depto
				departmentRepository.save(new Department("Humanities", deanJones));

				log.info("SQL to Create Natural Sciences");
				// almacena Departamento "Natural Sciences" con deanMartin como jefe de depto
				departmentRepository.save(new Department("Natural Sciences", deanMartin));

				log.info("SQL to Create Social Sciences");
				// almacena Departamento "Social Sciences" con deanJones como jefe de depto
				departmentRepository.save(new Department("Social Sciences", deanJones));
			}
		};
	}
}
