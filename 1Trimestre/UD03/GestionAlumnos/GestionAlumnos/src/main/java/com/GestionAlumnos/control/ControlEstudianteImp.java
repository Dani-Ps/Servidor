package com.GestionAlumnos.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.GestionAlumnos.model.Alumno;

@Controller
public class ControlEstudianteImp implements ControlEstudianteI {

	private List<Alumno> alumnos = new ArrayList<Alumno>();

	@PostMapping("/crear")
	public String añadir(@RequestParam String nombre, @RequestParam Integer edad, @RequestParam String curso) {
		try {
			Alumno a = new Alumno(nombre, edad, curso);
			if (existe(a)) {
				return mostrarExistError();
			} else {
				alumnos.add(a);
				return "exito";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@GetMapping("/crear")
	public String mostrarPaginaCrear(Model model) {
		model.addAttribute("alumno", new Alumno());
		return "crear";
	}

	@GetMapping("/listar")
	@Override
	public String listar(Model model) {
		List<Alumno> listaAlum = getAll();
		model.addAttribute("alumnos", listaAlum);
		return "listar";
	}

	@Override
	public boolean existe(Alumno a) {
		return alumnos.stream().anyMatch(alumno -> alumno.equals(a));
	}

	@Override
	public List<Alumno> getAll() {
		return alumnos;
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
