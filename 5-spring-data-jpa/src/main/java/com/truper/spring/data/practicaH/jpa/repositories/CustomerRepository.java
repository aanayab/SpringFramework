package com.truper.spring.data.practicaH.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truper.spring.data.practicaH.jpa.entity.Customer;

// Extiende de JpaRepository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	// Define los metodos conforme la practica lo requiera

}