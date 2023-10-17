package com.GestionAlumnos.control;

import java.util.List;

import org.springframework.ui.Model;

import com.GestionAlumnos.model.Alumno;
import com.GestionAlumnos.model.Alumno.Curso;

public interface ControlEstudianteI {
	
	public String listar(Model model);
	public String añadir(String nombre, Integer edad, String curso );
	public String eliminar(String nombre);
	String editar(String nombre, Model model);

	
	

}
