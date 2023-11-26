package com.example.demo.configuracion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entidad.Autor;
import com.example.demo.entidad.Libro;
import com.example.demo.enumerado.Categoria;
import com.example.demo.repositorio.AutorRepositorio;

import com.github.javafaker.Faker;

@Component
public class InicializadorDatos implements CommandLineRunner  {
	 @Autowired
	    private AutorRepositorio autorRepository;

	@Override
	public void run(String... args) throws Exception {

		 Faker faker = new Faker();
		 
		 
		 List<Categoria> listaCategorias =Arrays.asList(Categoria.values());
	
	        List<Autor> autores = new ArrayList<>();

	        // Crear 10 autores
	        for (int i = 0; i < 10; i++) {
	            Autor autor = new Autor();
	            autor.setNombre(faker.name().fullName());
	            autores.add(autor);

	            // Para cada autor, crear de 1 a 3 libros
	            int numLibros = faker.number().numberBetween(1, 4);
	            for (int j = 0; j < numLibros; j++) {
	                Libro libro = new Libro();
	                libro.setTitulo(faker.book().title());
	                libro.setAutor(autor);
	                libro.setCategoria(listaCategorias.get( faker.random().nextInt(listaCategorias.size())));
	                autor.getLibros().add(libro);
	            }
	        }

	        autorRepository.saveAll(autores);
	}

}
