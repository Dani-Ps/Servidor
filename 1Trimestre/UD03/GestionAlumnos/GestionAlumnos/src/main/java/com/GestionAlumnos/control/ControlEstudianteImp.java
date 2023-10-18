/**
 * Clase de implementación del control de estudiantes en el sistema de gestión de alumnos.
 */
package com.GestionAlumnos.control;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.GestionAlumnos.model.Alumno;

@Controller
public class ControlEstudianteImp implements ControlEstudianteI {

	// Lista para almacenar estudiantes
	private List<Alumno> alumnos = new ArrayList<Alumno>();

	/**
	 * Método para agregar un nuevo estudiante a la lista.
	 *
	 * @param nombre Nombre del estudiante.
	 * @param edad   Edad del estudiante.
	 * @param curso  Curso al que pertenece el estudiante.
	 * @return Vista a mostrar (éxito, existencia o error).
	 */
	@PostMapping("/crear")
	public String añadir(@RequestParam String nombre, @RequestParam Integer edad, @RequestParam String curso) {
		try {
			Alumno a = new Alumno(nombre, edad, curso);
			if (existe(a)) {
				return errorExiste();
			} else {
				alumnos.add(a);
				return "exito";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return errorGeneral();
		}
	}

	/**
	 * Método para mostrar la página de creación de estudiantes.
	 *
	 * @param model Modelo para almacenar datos de estudiante.
	 * @return Vista "crear".
	 */
	@GetMapping("/crear")
	public String mostrarPaginaCrear(Model model) {
		model.addAttribute("alumno", new Alumno());
		return "crear";
	}

	/**
	 * Método para listar todos los estudiantes.
	 *
	 * @param model Modelo para almacenar la lista de estudiantes.
	 * @return Vista "listar".
	 */
	@GetMapping("/listar")
	@Override
	public String listar(Model model) {
		List<Alumno> listaAlum = getAll();
		model.addAttribute("alumnos", listaAlum);
		return "listar";
	}

	@GetMapping("/buscar")
	public String listarPorNombre(@RequestParam("nombre") String nombre, Model model) {
		List<Alumno> listaAlum = getAllPorNombre(nombre);
		model.addAttribute("alumnos", listaAlum);
		return "listar";
	}

	/**
	 * Método que verifica si un estudiante ya existe en la lista.
	 *
	 * @param a Estudiante a verificar.
	 * @return Verdadero si el estudiante existe, falso en caso contrario.
	 */
	public boolean existe(Alumno a) {
		return alumnos.stream().anyMatch(alumno -> alumno.equals(a));
	}

	/**
	 * Método para obtener todos los estudiantes.
	 *
	 * @return Lista de estudiantes.
	 */
	public List<Alumno> getAll() {
		return alumnos;
	}

	public List<Alumno> getAllPorNombre(String nombre) {
		return alumnos.stream().filter(alumno -> alumno.getNombre().startsWith(nombre)).collect(Collectors.toList());
	}

	/**
	 * Método para eliminar un estudiante por nombre.
	 *
	 * @param nombre Nombre del estudiante a eliminar.
	 * @return Vista "eliminar" o vistas de error.
	 */
	@GetMapping("/eliminar/{nombre}")
	public String eliminar(@PathVariable String nombre) {
		try {
			Alumno alumEliminar = null;
			for (Alumno alumno : alumnos) {
				if (alumno.getNombre().equalsIgnoreCase(nombre)) {
					alumEliminar = alumno;
					break;
				}
			}

			if (alumEliminar != null) {
				alumnos.remove(alumEliminar);
				return "eliminar";
			} else {
				return "error/errorEliminar";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error/generalError";
		}
	}

	// Métodos para editar estudiantes

	@Override
	@GetMapping("/modificar/{nombre}")
	public String editar(@PathVariable String nombre, Model model) {
		try {
			Alumno alumEditar = null;
			for (Alumno alumno : alumnos) {
				if (alumno.getNombre().equalsIgnoreCase(nombre)) {
					alumEditar = alumno;
					break;
				}
			}
			if (alumEditar != null) {
				model.addAttribute("alumno", alumEditar);
				return "modificar";
			} else {
				return "error/editaError";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error/generalError";
		}
	}

	@PostMapping("/modificar/{nombre}")
	public String actualizarAlumno(@ModelAttribute Alumno alumno) {
		try {
			for (Alumno a : alumnos) {
				if (a.getNombre().equalsIgnoreCase(alumno.getNombre())) {

					a.setEdad(alumno.getEdad());
					a.setCurso(alumno.getCurso());
					break;
				}
			}
		} catch (Exception e) {
			return errorGeneral();
		}
		return editarExito();
	}

	@GetMapping("/filtrarPorCurso")
	public String filtrarPorCurso(@RequestParam("curso") String curso, Model model) {
		List<Alumno> estudiantesFiltrados = alumnos.stream().filter(alumno -> alumno.getCurso().equalsIgnoreCase(curso))
				.collect(Collectors.toList());
		model.addAttribute("alumnos", estudiantesFiltrados);
		return "listar";
	}

	@GetMapping("/promedioEdad")
	public String mostrarPromedioEdad(Model model) {
		if (alumnos.isEmpty()) {
			model.addAttribute("promedioEdad", 0);
		} else {
			double promedio = alumnos.stream().mapToDouble(Alumno::getEdad).average().orElse(0);
			model.addAttribute("promedioEdad", promedio);
		}
		return "promedioEdad";
	}

	@GetMapping("/index")
	public String volverInicio() {
		return "/index";
	}

	@GetMapping("/exist")
	public String errorExiste() {
		return "error/exist";
	}

	@GetMapping("/generalError")
	public String errorGeneral() {
		return "error/generalError";
	}

	@GetMapping("/errorEliminar")
	public String errorEliminar() {
		return "error/errorEliminar";
	}

	@GetMapping("/errorEditar")
	public String errorEditar() {
		return "error/errorEditar";
	}

	@GetMapping("/editarExito")
	public String editarExito() {
		return "editarExito";
	}

}
