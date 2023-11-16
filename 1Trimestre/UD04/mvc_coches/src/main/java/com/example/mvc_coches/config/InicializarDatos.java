package com.example.mvc_coches.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.mvc_coches.dao.CocheRepositorio;
import com.example.mvc_coches.modelo.Coche;

@Component
public class InicializarDatos implements CommandLineRunner{
	
	@Autowired
    CocheRepositorio repositorio;

	@Override
	public void run(String... args) throws Exception {
		Coche c = new Coche();
		c.setColor("Rojo");
		c.setMatricula("123456H");
		c.setMarca("Renault");
		
		repositorio.save(c);
		
	}

}
