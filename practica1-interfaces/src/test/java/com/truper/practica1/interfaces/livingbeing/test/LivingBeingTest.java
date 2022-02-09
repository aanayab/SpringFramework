package com.truper.practica1.interfaces.livingbeing.test;

import java.util.ArrayList;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.truper.practica1.interfaces.livingbeing.api.IBugEater;
import com.truper.practica1.interfaces.livingbeing.api.ILivingBeing;
import com.truper.practica1.interfaces.livingbeing.api.impl.Aardvark;
import com.truper.practica1.interfaces.livingbeing.api.impl.Animal;
import com.truper.practica1.interfaces.livingbeing.api.impl.Plant;
import com.truper.practica1.interfaces.livingbeing.api.impl.VenusFlyTrap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LivingBeingTest {

	@Test
	public void livingBeingTest() {
		log.info("livingBeingTest -------------------");

		// Implementar
		ILivingBeing livingBeing1 = new VenusFlyTrap();
		ILivingBeing livingBeing2 = new Aardvark();
		
		Assert.assertTrue(livingBeing1 instanceof ILivingBeing);
		Assert.assertTrue(livingBeing2 instanceof ILivingBeing);
		
		int a = 5;
		int b = 5;
		int suma = a+b;
		Assert.assertEquals(10, suma);
		
		Assert.assertThat(VenusFlyTrap.class.getSimpleName(),
				CoreMatchers.is(livingBeing1.getClass().getSimpleName()));
		Assert.assertTrue(livingBeing2.getClass().getSimpleName().equals(Aardvark.class.getSimpleName()));
		
		Assert.assertTrue(livingBeing1 instanceof IBugEater);
		Assert.assertTrue(livingBeing2 instanceof IBugEater);
		
		livingBeing1.born();
		livingBeing1.grow();
		livingBeing1.breed();
		livingBeing1.die();
		//livingBeing1.eatBug(); // La referencia no tiene el metodo eatBug(), pero el objeto si
		
		((IBugEater)livingBeing1).eatBug();
		
		IBugEater bugEater1 = (IBugEater) livingBeing1;
		bugEater1.eatBug();
	}

}
