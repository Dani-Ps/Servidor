package com.example.demo.controlador;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entidad.Curso;
import com.example.demo.entidad.Estudiante;
import com.example.demo.servicio.EstudianteServicio;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteControlador {

	@Autowired
	private EstudianteServicio estudianteServicio;

	@GetMapping
	public String listarEstudiantes(Model model) {
		model.addAttribute("estudiantes", estudianteServicio.obtenerTodos());
		return "estudiantes/lista";
	}

	@GetMapping("/nuevo")
	public String mostrarFormularioDeNuevoEstudiante(Model model) {
		model.addAttribute("estudiante", new Estudiante());
		return "estudiantes/formulario";
	}

	@PostMapping("/nuevo")
	public String guardarEstudiante(@Valid Estudiante estudiante, BindingResult result) {
		if (result.hasErrors()) {
			return "estudiantes/formulario";
		}
		estudianteServicio.guardarEstudiante(estudiante);
		return "redirect:/estudiantes";
	}

	@GetMapping("/ver/{id}")
	public String verDetalleEstudiante(@PathVariable Long id, Model model) {
		Optional<Estudiante> estudiante = estudianteServicio.obtenerPorId(id);

		if (estudiante.isPresent()) {
			model.addAttribute("estudiante", estudiante.get());
			model.addAttribute("cursos", estudianteServicio.obtenerTodosLosCursos().stream().distinct());

			return "estudiantes/detalle-estudiante";
		} else {
			return "redirect:/error";
		}
	}

	@PostMapping("/agregarCurso")
	public String agregarCursoAEstudiante(@RequestParam Long estudianteId,  @RequestParam(required = false) Long cursoId,
			RedirectAttributes redirectAttributes) {
		try {
			if (estudianteId == null || cursoId == null) {
				// Lógica para manejar IDs nulos
				redirectAttributes.addFlashAttribute("mensajeError", "Datos inválidos.");
				return "redirect:/estudiantes/ver/" + estudianteId;
			}
			estudianteServicio.agregarCursoAEstudiante(estudianteId, cursoId);
			redirectAttributes.addFlashAttribute("mensajeExito", "Curso agregado exitosamente al estudiante.");
		} catch (RuntimeException e) {
			// Manejar la excepción y agregar mensaje de error
			// Considerar un mensaje de error más amigable o genérico
			redirectAttributes.addFlashAttribute("mensajeError", "Error al agregar el curso.");

		} 

		// Redirigir a la página de detalles del estudiante o a otra página relevante
		return "redirect:/estudiantes/ver/" + estudianteId;
	}
	
	@GetMapping("/curso")
    public String mostrarCursos(@RequestParam(value = "cursoId", required = false) Long cursoId, Model model) {

		List<Curso> cursos = estudianteServicio.obtenerTodosLosCursos();
        model.addAttribute("cursos", cursos);

        if (cursoId != null) {
            Set<Estudiante> estudiantes = estudianteServicio.obtenerEstudiantesPorCurso(cursoId);
            model.addAttribute("estudiantes", estudiantes);
            model.addAttribute("curso", estudianteServicio.buscarCursoPorId(cursoId));
        }

        return "estudiantes/estudiante-curso";
    }

}
