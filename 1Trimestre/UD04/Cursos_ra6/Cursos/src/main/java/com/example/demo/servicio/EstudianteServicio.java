package com.example.demo.servicio;

import java.util.List;
import java.util.Set;

import com.example.demo.entidad.Estudiante;

public interface EstudianteServicio {
	 	Estudiante guardarEstudiante(Estudiante estudiante);
	 	Estudiante actualizarEstudiante(Long id, Estudiante estudiante);
	    void eliminarEstudiante(Long id);
	    Estudiante buscarEstudiantePorId(Long id);
	    List<Estudiante> listarTodosLosEstudiantes();
	    List<Estudiante> buscarEstudiantesPorNombre(String nombre);
	    Set<Estudiante> obtenerEstudiantesPorCurso(Long idCurso);
	    void agregarCursoAEstudiante(Long idEstudiante, Long idCurso);
	    
}
