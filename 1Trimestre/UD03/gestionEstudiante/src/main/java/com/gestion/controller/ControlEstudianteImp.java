package com.gestion.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gestion.model.Alumno;
import com.gestion.model.Alumno.Curso;

@Controller
public class ControlEstudianteImp implements ControlEstudianteI {

	private List<Alumno> alumnos = new ArrayList<Alumno>();

	@Override
	public boolean listar() {
		return false;
	}

	@Override
	public boolean existe(Alumno a) {
		return alumnos.stream().anyMatch(alumno -> alumno.equals(a));
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

	@PostMapping("/crear")
	@Override
	public String añadir(String nombre, Integer edad, Curso curso) {
		try {
			Alumno a = new Alumno(nombre, edad, curso);
			if (existe(a)) {
				mostrarExistError();
			} else {
				alumnos.add(a);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return "exito";
	}

	@Override
	public boolean leer(Alumno a) {
		// TODO Auto-generated method stub
		return false;
	}

	@GetMapping("/mostrarExistError")
	public String mostrarExistError() {
		return "error/exist";
	}
}