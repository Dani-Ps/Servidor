package com.example.libros.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.libros.entidad.Categoria;
import com.example.libros.entidad.Libro;

@Service
public class ServicioLibros {
	
	private List<Libro> libros;
	
	public ServicioLibros() {
		libros = new ArrayList<Libro>();
	}
	
	public boolean guardarLibro(Libro libro) {
		return libros.add(libro);
	}
	
	public  List<Libro> obtenerLibros(){
		return libros;
	}

	public List<Libro> obtenerLibros(String categoria) {
		return libros.stream().filter(d -> d.getCategoria().equals(Categoria.valueOf(categoria))).collect(Collectors.toList());
	}
	

}
