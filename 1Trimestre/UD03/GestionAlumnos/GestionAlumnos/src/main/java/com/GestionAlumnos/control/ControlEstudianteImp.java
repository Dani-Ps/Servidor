package com.GestionAlumnos.control;

import java.util.ArrayList;
import java.util.List;

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

	private List<Alumno> alumnos = new ArrayList<Alumno>();

// CREAR
	@PostMapping("/crear")
	public String añadir(@RequestParam String nombre, @RequestParam Integer edad, @RequestParam String curso) {
		try {
			Alumno a = new Alumno(nombre, edad, curso);
			if (existe(a)) {
				return "exist";
			} else {
				alumnos.add(a);
				return "exito";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "generalError";
		}
	}

	@GetMapping("/crear")
	public String mostrarPaginaCrear(Model model) {
		model.addAttribute("alumno", new Alumno());
		return "crear";
	}
	// LISTAR

	@GetMapping("/listar")
	@Override
	public String listar(Model model) {
		List<Alumno> listaAlum = getAll();
		model.addAttribute("alumnos", listaAlum);
		return "listar";
	}

	public boolean existe(Alumno a) {
		return alumnos.stream().anyMatch(alumno -> alumno.equals(a));
	}

	public List<Alumno> getAll() {
		return alumnos;
	}
	// ELIMINAR
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


	// EDITAR

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
	        if (alumEditar!= null) {
				model.addAttribute("alumno", alumEditar);
				return "modificar";
			} else {
		        return "error/editaError";

			}
	        
		} catch (Exception e) {
			 e.printStackTrace();
		       return "error/generalError";		}
		
	}
	
	@PostMapping("/modificar/{nombre}")
	public String actualizarAlumno(@ModelAttribute Alumno alumno) {
	    try {
	        // Buscar al alumno en la lista por el nombre
	        for (Alumno a : alumnos) {
	        	System.out.println("Metodo post modificar");
	            if (a.getNombre().equalsIgnoreCase(alumno.getNombre())) {
	                // Actualizar los campos de edad y curso
	                a.setEdad(alumno.getEdad());
	                a.setCurso(alumno.getCurso());
	                return "editarExito";
	            }
	        }
	        return "error/editaError"; // Si no se encuentra el alumno
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "error/generalError";
	    }
	}


	public List<Alumno> filtrar() {
		return null;
	}

	public boolean leer(Alumno a) {
		return false;
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

}
