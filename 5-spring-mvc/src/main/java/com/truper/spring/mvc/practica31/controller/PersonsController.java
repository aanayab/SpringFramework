package com.truper.spring.mvc.practica31.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.truper.spring.validation.practica30.parte1.domain.Person;
import com.truper.spring.validation.practica30.parte1.domain.Persons;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// Anotar Controller
@Controller
// Anotar request mapping "/persons"
@RequestMapping("/persons")
public class PersonsController {

	private List<Person> persons = Collections.synchronizedList(new ArrayList<Person>());

	@PostConstruct
	private void init() {
		for (int i = 0; i < 3; i++) {
			Person p = new Person();
			p.setId(i + 1);
			p.setName("Ivan " + (i + 1));
			p.setAge(28 + i + 1);
			persons.add(p);
		}
	}

	// Anotar request mapping "/", "", con metodo GET y produciendo json y xml
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody Persons getAllPersons() {
		return new Persons(persons);
	}

	// Anotar request mapping "/{id}", con metodo GET y produciendo json y xml
	// Anotar response status ok
	@RequestMapping(value = { "/{id}" }, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(code = HttpStatus.OK)
	public @ResponseBody Person getPerson(@PathVariable Integer id) {
		return persons.get(id - 1);
	}

	// Anotar request mapping "/", "", con metodo POST y produciendo json y xml
	// Anotar response status NO CONTENT
	@RequestMapping(value = { "/", "" }, method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void createPerson(@RequestBody Person person) {
		person.setId(persons.size() + 1);
		persons.add(person);
	}

	// Anotar request mapping "/{id}", con metodo PUT y produciendo json y xml
	// Anotar response status ok
	@RequestMapping(value = { "/{id}" }, method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(code = HttpStatus.OK)
	public @ResponseBody Person updatePerson(@PathVariable Integer id, @RequestBody Person person) {
		Person p = persons.get(id - 1);
		persons.remove(p);
		p.setId(p.getId());
		p.setName(person.getName());
		p.setAge(person.getAge());
		persons.add(id - 1, p);

		return p;
	}

	// Anotar request mapping "/{id}", con metodo DELETE y produciendo json y xml
	// Anotar response status ok
	@RequestMapping(value = { "/{id}" }, method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deltePerson(@PathVariable Integer id) {
		Person p = persons.get(id - 1);
		persons.remove(p);

		int i = 0;
		for (Person pe : persons) {
			pe.setId(++i);
		}
	}

}
