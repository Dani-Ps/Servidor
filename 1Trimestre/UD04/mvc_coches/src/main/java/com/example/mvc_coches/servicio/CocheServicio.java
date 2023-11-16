package com.example.mvc_coches.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mvc_coches.dao.CocheRepositorio;
import com.example.mvc_coches.modelo.Coche;

import jakarta.validation.Valid;

@Service
public class CocheServicio {

	@Autowired
	private CocheRepositorio repositorio;
	
	/**
	 * Recupera todos los coches disponibles en la base de datos.
	 * @return Lista de todos los coches.
	 */
	public List<Coche>obtenerTodoslosCoches() {
		return (List<Coche>)repositorio.findAll();
	}

	/**
	 * Guarda un coche en la base de datos. Si el coche ya existe (basado en el ID), será actualizado.
	 * @param coche El coche a guardar o actualizar.
	 */
	public void guardar(@Valid Coche coche) {
		repositorio.save(coche);
		
	}

	/**
	 * Busca un coche por su ID en la base de datos.
	 * @param id El ID del coche que se quiere recuperar.
	 * @return El coche encontrado.
	 */
	public Coche obtenerCochePorId(Integer id) {
		return repositorio.findById(id).get();
	}

	/**
	 * Elimina un coche de la base de datos por su ID.
	 * @param id El ID del coche a eliminar.
	 */
	public void eliminarProducto(Integer id) {
		 repositorio.deleteById(id);
	}
	
}
