package com.gestion.controller;

import java.util.List;

import org.springframework.ui.Model;

import com.gestion.model.Alumno;
import com.gestion.model.Alumno.Curso;

public interface ControlEstudianteI {
	
	public String listar(Model model);
	public String añadir(String nombre, Integer edad, Curso curso );
	public boolean leer(Alumno a);
	public List<Alumno> getAll();
	public boolean eliminar(String nombre);
	public List<Alumno> filtrar();
	boolean existe(Alumno a);
	
	
	

}
