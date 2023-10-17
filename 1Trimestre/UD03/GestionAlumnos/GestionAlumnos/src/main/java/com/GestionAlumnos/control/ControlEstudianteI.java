package com.GestionAlumnos.control;

import java.util.List;

import org.springframework.ui.Model;

import com.GestionAlumnos.model.Alumno;
import com.GestionAlumnos.model.Alumno.Curso;

public interface ControlEstudianteI {
	
	public String listar(Model model);
	public String añadir(String nombre, Integer edad, String curso );
	public boolean leer(Alumno a);
	public List<Alumno> getAll();
	public boolean eliminar(String nombre);
	public List<Alumno> filtrar();
	boolean existe(Alumno a);
	
	
	

}
