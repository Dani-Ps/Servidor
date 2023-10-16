package com.gestion.controller;

import java.util.List;

import com.gestion.model.Alumno;

public interface ControlEstudianteI {
	
	public boolean listar();
	public boolean añadir(Alumno a);
	public Alumno leer(String nombre);
	public List<Alumno> getAll();
	public boolean eliminar(String nombre);
	public List<Alumno> filtrar();
	
	
	

}
