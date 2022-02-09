package com.truper.spring.core.practicaC.test.filteringcomponents;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.truper.spring.core.practicaC.filteringcomponents.bean.BabyBiker;
import com.truper.spring.core.practicaC.filteringcomponents.bean.Bike;
import com.truper.spring.core.practicaC.filteringcomponents.bean.Biker;
import com.truper.spring.core.practicaC.filteringcomponents.bean.Car;
import com.truper.spring.core.practicaC.filteringcomponents.bean.repository.BikeRepository;
import com.truper.spring.core.practicaC.filteringcomponents.bean.repository.CarRepository;
import com.truper.spring.core.practicaC.filteringcomponents.config.FilteringComponentsConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FilteringComponentsConfig.class)
public class FilteringComponents2Test {

	@Autowired
	private Bike bike;

	@Autowired
	private Car car;

	@Autowired
	@Nullable
	private Biker biker;

	@Autowired
	@Nullable
	private BabyBiker babyBiker;

	@Autowired
	@Nullable
	private BikeRepository bikeRepository;

	@Autowired
	@Nullable
	private CarRepository carRepository;

	@Before
	public void before() {
		Assert.assertNull(bikeRepository);
		Assert.assertNull(carRepository);

		Assert.assertNull(biker);
		Assert.assertNull(babyBiker);

		Assert.assertNotNull(car);
		Assert.assertNotNull(bike);
	}

	@Test
	public void filteringComponentsTest1() {

		log.info("filteringComponentsTest1 -------------------");

		log.info("bikeRepository: {}", bikeRepository);
		log.info("carRepository: {}", carRepository);

		log.info("---");

		log.info("biker: {}", biker);
		log.info("babyBiker: {}", babyBiker);

		log.info("---");

		log.info("car: {}", car);
		log.info("bike: {}", bike);

	}

}
