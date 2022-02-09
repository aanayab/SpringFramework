package com.truper.spring.data.practicaH.jpa.repositories;

import java.util.List;

import com.truper.spring.data.practicaH.jpa.entity.Staff;

public interface StaffCustomRepository {
	
	// Define un metodo customizado "searchStaffByFullname" para buscar Staff por lastname y firstname
	// la implementacion debe usar JPQL
	List<Staff> buscaStaffPorNombreCompleto(String lastname, String firstName);
	
	// Define un metodo customizado "searchNativeStaffByFullname" para buscar Staff por lastname y firstname
	// la implementacion debe usar SQL nativo
	List<Staff> buscaStaffPorNombreCompletoUsandoConsultaNativa(String firstName, String lastname);
}