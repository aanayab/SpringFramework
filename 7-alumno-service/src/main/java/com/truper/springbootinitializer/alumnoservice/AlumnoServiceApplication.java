package com.truper.springbootinitializer.alumnoservice;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@SpringBootApplication
public class AlumnoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlumnoServiceApplication.class, args);
	}

	@Autowired
	private AlumnoService alumnoService;

	@GetMapping("/ex-novias")
	public List<Alumno> getAllAlumnos() {
		return alumnoService.fetchAll();
	}

	@Bean
	public Supplier<Integer> random(@Value("31") int max, @Value("20") int min) {
		return () -> Double.valueOf(Math.floor(Math.random() * (max - min + 1)) + min).intValue();
	}

}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Alumno {
	private String nombre;
	private int edad;
}

@Service
class AlumnoService {
	
	@Autowired
	private Supplier<Integer> random;
	
	public List<Alumno> fetchAll() {
		return Stream.of("Karla", "Yuri", "Tania", "Ilse", "Erika", "Carolina", "Ivonne")
					 .map(str -> Alumno.builder().nombre(str).edad(random.get()).build())
					 .collect(Collectors.toList());
	}
}
