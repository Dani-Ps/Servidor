package com.pablo.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pablo.crud.model.Estudiante;
import com.pablo.crud.service.EstudianteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class EstudianteController {
	
	/**
	 * Inyeccion de el servicio de estudiantes
	 */
	@Autowired
	private EstudianteService estudianteService;
	
	/**
	 * Controlador get (/) que lleva a la pagina index.html
	 * 
	 * @return view index
	 */
	@GetMapping
	public String index() {
		return "index";
	}
	
	/**
	 * Controlador get (/estudiantes) que lleva a la pagina estudiantes.html 
	 * 
	 * @param model
	 * @return view estudiantes
	 */
	@GetMapping("/estudiantes")
	public String listarEstudiantes(Model model) {
		model.addAttribute("estudiantes", estudianteService.listarEstudiantes());
		return "estudiantes";
	}
	
	/**
	 * Controlador get (/estudiante/nuevo) que lleva a la pagina form_estudiante, para crear un estudiante
	 * 
	 * @param model
	 * @return view form_estudiante
	 */
	@GetMapping("/estudiante/nuevo")
	public String estudianteNuevo(Model model) {
		model.addAttribute("estudiante", new Estudiante());
		return "form_estudiante";
	}
	
	@PostMapping("/agregar")
	public String estudianteNuevoPost(@Valid @ModelAttribute Estudiante estudiante, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "form_estudiante";
		}
		
		estudianteService.guardarEstudiante(estudiante);
		return "redirect:/estudiantes";
	}
	
	@GetMapping("/estudiante/editar/{id}")
	public String estudianteEditar(@PathVariable Integer id, Model model) {
		model.addAttribute("estudiante", estudianteService.encontrarPorId(id));
		return "form_estudiante";
	}
	
	@GetMapping("/estudiante/eliminar/{id}")
	public String estudianteEliminar(@PathVariable Integer id, Model model) {
		estudianteService.eliminarEstudiantePorId(id);
		return "redirect:/estudiantes";
	}
}
