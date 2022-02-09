package com.truper.spring.core.practica4.test.movies;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.truper.spring.core.practica4.movies.model.Movie;
import com.truper.spring.core.practica4.movies.service.MovieListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MoviesSpringTest {

	private static ApplicationContext applicationContext;

	@BeforeClass
	public static void beforeClass() {
		// Instancia ApplicationContext
	}

	@AfterClass
	public static void afterClass() {
		// Cerrar ApplicationContext
	}

	@Test
	public void moviesSpringTest() {

		log.info("moviesSpringTest -------------------");

		// Implementa
		/*MovieListener movieListener = null; // se lo pido a spring

		Assert.assertNotNull(movieListener);
		
		Movie movie = movieListener.buscarPelicula("El club de la pelea");

		Assert.assertNotNull(movie);
		Assert.assertEquals("El club de la pelea", movie.getTitulo());*/
	}
}
