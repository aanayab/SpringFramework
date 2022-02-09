package com.truper.spring.core.practica21.test.spel;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.math3.complex.Complex;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.truper.spring.core.practica19.javaconfig.bean.api.QuadraticEquationResult;
import com.truper.spring.core.practica21.spel.bean.Chicharronera;
import com.truper.spring.core.practica21.spel.bean.GuessNumber;
import com.truper.spring.core.practica21.spel.bean.Magician;
import com.truper.spring.core.practica21.spel.bean.MyBeanResolver;
import com.truper.spring.core.practica21.spel.configuration.ApplicationConfig;
import com.truper.spring.core.practica21.spel.model.Inventor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VariablesFunctionsBeanReferencesExpressionsTest_7 {

	private static ExpressionParser spelParser = new SpelExpressionParser();

	private static StandardEvaluationContext springContext;

	private static ApplicationContext applicationContext;

	@BeforeClass
	public static void setUp() {
		applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		springContext = new StandardEvaluationContext();
		
		// asignar al 'springContext' el bean resolver 'MyBeanResolver' que se encuentra en el application context
		MyBeanResolver resolver = new MyBeanResolver();
		resolver.setApplicationContext(applicationContext);
		
		springContext.setBeanResolver(resolver);
	}

	@Test
	public void variablesExpressionsTest() {

		log.info("variablesExpressionsTest -------------------");

		Magician magician = applicationContext.getBean(Magician.class);

		springContext.setVariable("magicNumber", magician.getInitialNumber()); // solo analizar

		// defnir y obtener el valor de una expresion que acceda al bean guessNumberBean definido en el aplication-context.xml
		// y recupere el valor de la propiedad randomNumber
		Integer randomNumber = spelParser.parseExpression("@guessNumberBean.randomNumber").getValue(springContext, Integer.class);

		springContext.setVariable("randomNumber", randomNumber); // solo analizar

		// -------------------------------------

		Boolean isCorrectNumber = spelParser.parseExpression("#randomNumber == #magicNumber").getValue(springContext,
				Boolean.class); // solo analizar
		
		Assert.assertNotNull(isCorrectNumber);
		
		log.info("isCorrectNumber: {}", isCorrectNumber);

		log.info("magician.initialNumber: {}", magician.getInitialNumber());
		
		log.info("guessNumberBean.randomNumber: {}", randomNumber);
	}

	@Test
	@SneakyThrows
	public void functionsExpressionsTest() {

		log.info("functionsExpressionsTest -------------------");

		// analizar setVariable's
		springContext.setVariable("a", 5);
		springContext.setVariable("b", 4);
		springContext.setVariable("c", -10);

		// registra una funcion llamada 'chicharronera' que invoque al metodo 'calculate' de la clase Chicharronera
		// T(com.truper.spring.core.practica21.spel.bean.Chicharronera).calculate(a,b,c)
		
		Method method = Chicharronera.class.getDeclaredMethod("calculate", double.class, double.class, double.class);
		springContext.registerFunction("chicharronera", method);
		

		QuadraticEquationResult expectedResult = QuadraticEquationResult.builder().x1(new Complex(1.0697, 0.0))
				.x2(new Complex(-1.8697, 0.0)).build(); // solo analiza

		// definir y obtener el valor de la expresion que invoque a la funcion 'chicharronera' tomando como argumentos
		// las variables 'a', 'b' y 'c'
		QuadraticEquationResult quadraticEquationResult = spelParser.parseExpression("#chicharronera(#a,#b,#c)")
				.getValue(springContext, QuadraticEquationResult.class);

		Assert.assertNotNull(quadraticEquationResult);
		
		Assert.assertEquals(expectedResult, quadraticEquationResult);
		
		log.info("quadraticEquationResult: {}", quadraticEquationResult);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void beanReferencesExpressionsTest() {

		log.info("beanReferencesExpressionsTest -------------------");

		GuessNumber guessNumber = spelParser.parseExpression("@guessNumberBean").getValue(springContext,
				GuessNumber.class); //solo analiza
		
		Assert.assertNotNull(guessNumber);
		
		log.info("guessNumber: {}", guessNumber);

		// -------------------------------------

		Inventor tesla = spelParser.parseExpression("@teslaBean").getValue(springContext,
				Inventor.class); //solo analiza
		
		Assert.assertNotNull(tesla);
		
		log.info("tesla: {}", tesla);

		// -------------------------------------

		Integer inventionsLength = spelParser.parseExpression("@teslaBean.inventions.length").getValue(springContext,
				int.class); //solo analiza
		
		Assert.assertNotNull(inventionsLength);
		
		log.info("inventionsLength: {}", inventionsLength);

		// -------------------------------------

		List<String> inventions = spelParser.parseExpression("@teslaBean.inventions").getValue(springContext,
				List.class); //solo analiza
		
		Assert.assertNotNull(inventions);
		
		log.info("inventions: {}", inventions);

		Assert.assertEquals(3, inventions.size());
	}

}
