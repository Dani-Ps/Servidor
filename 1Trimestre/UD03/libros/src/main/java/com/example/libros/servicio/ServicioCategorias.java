package com.example.libros.servicio;

import java.util.List;

import com.example.libros.entidad.Categoria;

public class ServicioCategorias {

	private static Categoria[] categorias = Categoria.values();

	public static Categoria[] getCategorias() {
		return categorias;
	}

}
