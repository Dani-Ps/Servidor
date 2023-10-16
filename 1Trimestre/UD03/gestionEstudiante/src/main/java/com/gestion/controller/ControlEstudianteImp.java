package com.gestion.controller;

import java.util.ArrayList;
import java.util.List;

import com.gestion.model.Alumno;

public class ControlEstudianteImp implements ControlEstudianteI{

	private List<Alumno> alumnos = new ArrayList<Alumno>(); 

	@Override
	public boolean listar() {
		return false;
	}

	@Override
	public boolean añadir(Alumno a) {
		
		return false;
	}

	@Override
	public Alumno leer(String nombre) {
		return null;
	}

	@Override
	public List<Alumno> getAll() {
		return null;
	}

	@Override
	public boolean eliminar(String nombre) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Alumno> filtrar() {
		// TODO Auto-generated method stub
		return null;
	}

}
