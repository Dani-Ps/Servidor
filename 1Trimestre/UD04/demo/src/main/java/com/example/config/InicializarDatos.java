package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.dao.EmpleadoRepo;
import com.example.model.Empleado;

@Component
public class InicializarDatos implements CommandLineRunner{

	
	@Autowired
	EmpleadoRepo repositorio;
	@Override
	public void run(String... args) throws Exception {
		Empleado e = new Empleado();
		e.setNombre("Dani");
		e.setEmail("daniperez439@gmail.com");
		
	}
	
	

}
