package com.truper.springboot;

import java.util.Arrays;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.truper.springboot.mongo.documents.UserEntity;
import com.truper.springboot.mongo.repositories.UserEntityRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Supplier<Integer> random(@Value("31") int max, @Value("20") int min) {
		return () -> {
			return Double.valueOf(Math.floor(Math.random() * (max - min + 1) + min)).intValue();
		};
	}

	@Bean
	public CommandLineRunner startup(Supplier<Integer> random, UserEntityRepository userEntityRepository) {

		return (args) -> {
			
			long inserted = Arrays.asList("Iker", "Alejandra", "Fabiola", "Tania", "Karla", "Paula")
				  .stream()
				  .map(name -> UserEntity.builder().name(name).age(random.get()).username(name.toLowerCase()).build())
				  .peek(ue -> ue.setPassword("$2a$12$mC84DfxQCnohm9u1CKNEaurzOUI.Bqy3M/4aHu25/a6KlRxvRu5JC")) // password
				  .peek(ue -> ue.setRoles(Arrays.asList("ROLE_USER")))
				  .map(ue -> userEntityRepository.save(ue))
				  .count()
				  ;
			System.out.println("Users inserted: " + inserted);
			
			inserted = Arrays.asList("Admin")
					  .stream()
					  .map(name -> UserEntity.builder().name(name).age(random.get()).username(name.toLowerCase()).build())
					  .peek(ue -> ue.setPassword("$2a$12$05k07Z8DVWwBkrRLxsjW7uXX6fCfGuW52DjO4w6kQUYI9U5sK.iYO")) // admin
					  .peek(ue -> ue.setRoles(Arrays.asList("ROLE_ADMIN")))
					  .map(ue -> userEntityRepository.save(ue))
					  .count()
					  ;
			System.out.println("Admins inserted: " + inserted);
			
			inserted = Arrays.asList("Root")
					  .stream()
					  .map(name -> UserEntity.builder().name(name).age(random.get()).username(name.toLowerCase()).build())
					  .peek(ue -> ue.setPassword("$2a$12$8wXuiYR.TBrGgM/6QBhsOOq0S2bZOYPjk4mfd6529v0aEKN3f/Yqm")) // root
					  .peek(ue -> ue.setRoles(Arrays.asList("ROLE_ROOT")))
					  .map(ue -> userEntityRepository.save(ue))
					  .count()
					  ;
			System.out.println("Roots inserted: " + inserted);
			
			userEntityRepository.findAll().forEach(System.out::println);
		};
	}

}
