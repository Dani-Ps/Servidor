/**
 * Clase de implementación del control de estudiantes en el sistema de gestión de alumnos.
 */
package com.GestionAlumnos.control;

import java.util.ArrayList;
import java.util.Arrays;
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
			// Crear un objeto Alumno con los parámetros recibidos
			Alumno a = new Alumno(nombre, edad, curso);
			if (existe(a)) {
				// El estudiante ya existe, mostrar un error.
				return errorExiste();
			} else {
				// Agregar el estudiante a la lista.
				alumnos.add(a);
				return "exito"; // Éxito al agregar el estudiante.
			}
		} catch (Exception e) {
			e.printStackTrace();
			return errorGeneral(); // Error general.
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
		return "crear"; // Redirigir a la vista "crear".
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
		return "listar"; // Redirigir a la vista "listar".
	}

	@GetMapping("/buscar")
	public String listarPorNombre(@RequestParam("nombre") String nombre, Model model) {
		List<Alumno> listaAlum = getAllPorNombre(nombre);

		// Verificar si la lista de alumnos está vacía
		if (listaAlum.isEmpty()) {
			// Redirigir a un template de error
			return noExiste(); // No existe ningún estudiante con el nombre proporcionado.
		}

		model.addAttribute("alumnos", listaAlum);
		return "listar"; // Redirigir al template de lista.
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

	/**
	 * Método para obtener una lista de estudiantes por nombre.
	 *
	 * @param nombre Nombre para filtrar la lista de estudiantes.
	 * @return Lista de estudiantes que coinciden con el nombre proporcionado.
	 */
	public List<Alumno> getAllPorNombre(String nombre) {
		return alumnos.stream().filter(alumno -> alumno.getNombre().startsWith(nombre)).collect(Collectors.toList());
	}

	/**
	 * Método para eliminar un estudiante por nombre.
	 *
	 * @param nombre Nombre del estudiante a eliminar.
	 * @return Vista "eliminar" si se elimina con éxito, vistas de error en caso
	 *         contrario.
	 */
	@GetMapping("/eliminar/{nombre}")
	public String eliminar(@PathVariable String nombre) {
		try {
			// Creo el objeto alumno que será el eliminado.
			Alumno alumEliminar = null;
			// Busco por el nombre en la lista al alumno.
			for (Alumno alumno : alumnos) {
				if (alumno.getNombre().equalsIgnoreCase(nombre)) {
					alumEliminar = alumno;
					break;
				}
			}
			if (alumEliminar != null) {
				// Eliminar el estudiante de la lista y redirigir a la vista "eliminar".
				alumnos.remove(alumEliminar);
				return "eliminar";
			} else {
				// Redirigir a una vista de error en caso de que el estudiante no se encuentre.
				return "error/errorEliminar";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error/generalError"; // Error general.
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
				// Mostrar la vista de edición y pasar el estudiante como atributo.
				model.addAttribute("alumno", alumEditar);

			}
			return "modificar";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/generalError"; // Error general.
		}
	}

	@PostMapping("/modificar/{nombre}")
	public String actualizarAlumno(@ModelAttribute Alumno alumno, @PathVariable String nombre) {
		try {
			for (Alumno a : alumnos) {
				if (a.getNombre().equalsIgnoreCase(nombre)) {
					// Actualizar los datos del estudiante con los nuevos valores.
					a.setNombre(alumno.getNombre()); // Actualizar el nombre
					a.setEdad(alumno.getEdad());
					a.setCurso(alumno.getCurso());
					break;
				}
			}
		} catch (Exception e) {
			return errorGeneral();
		}
		return editarExito(); // Redirigir a la vista de edición exitosa.
	}

	@GetMapping("/filtrarPorCurso")
	public String filtrarPorCurso(@RequestParam(value = "curso", required = false) String[] cursos, Model model) {
	    if (cursos != null && cursos.length > 0) {
	        // Al menos un curso se ha seleccionado, filtra por los cursos seleccionados
	        List<Alumno> estudiantesFiltrados = alumnos.stream()
	                .filter(alumno -> Arrays.stream(cursos)
	                        .anyMatch(curso -> alumno.getCurso().equalsIgnoreCase(curso)))
	                .collect(Collectors.toList());
	        model.addAttribute("alumnos", estudiantesFiltrados);
	    } else {
	        // No se seleccionó ningún curso, muestra todos los estudiantes sin filtrar
	        model.addAttribute("alumnos", alumnos);
	    }
	    return "listar"; // Redirigir a la vista de lista.
	}


	@GetMapping("/promedioEdad")
	public String mostrarPromedioEdad(Model model) {
		if (alumnos.isEmpty()) {
			model.addAttribute("promedioEdad", 0.0); // Cuando no hay alumnos, se muestra 0.0 como un número
		} else {
			double promedio = alumnos.stream().mapToDouble(Alumno::getEdad).average().orElse(0);
			String promedioFormateado = String.format("%.1f", promedio); // Formatea el promedio con un decimal
			model.addAttribute("promedioEdad", promedioFormateado);
		}
		return "promedioEdad"; // Redirigir a la vista "promedioEdad".
	}

	// Metodos para redirigir
	@GetMapping("/index")
	public String volverInicio() {
		return "/index"; // Redirigir a la vista "index".
	}

	@GetMapping("/exist")
	public String errorExiste() {
		return "error/exist"; // Redirigir a la vista de error "exist".
	}

	@GetMapping("/generalError")
	public String errorGeneral() {
		return "error/generalError"; // Redirigir a la vista de error "generalError".
	}

	@GetMapping("/errorEliminar")
	public String errorEliminar() {
		return "error/errorEliminar"; // Redirigir a la vista de error "errorEliminar".
	}

	@GetMapping("/errorEditar")
	public String errorEditar() {
		return "error/errorEditar"; // Redirigir a la vista de error "errorEditar".
	}

	@GetMapping("/editarExito")
	public String editarExito() {
		return "editarExito"; // Redirigir a la vista de éxito "editarExito".
	}

	@GetMapping("/noExiste")
	public String noExiste() {
		return "error/noExiste"; // Redirigir a la vista de error "noExiste".
	}

}
