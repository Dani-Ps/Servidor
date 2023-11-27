package com.example.demo.servicio.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Curso;
import com.example.demo.entidad.Estudiante;
import com.example.demo.repositorio.CursoRepositorio;
import com.example.demo.repositorio.EstudianteRepositorio;
import com.example.demo.servicio.EstudianteServicio;

import jakarta.transaction.Transactional;

@Service
public class EstudianteServicioImplementacion implements EstudianteServicio {
	@Autowired
	EstudianteRepositorio estudianteRepositorio;
	@Autowired
	CursoRepositorio cursoRepositorio;

	/**
	 * Metodo para guardar un estudiante
	 * 
	 * @param estudiante
	 * @return estudiante
	 */
	@Override
	public Estudiante guardarEstudiante(Estudiante estudiante) {
		estudianteRepositorio.save(estudiante);
		return estudiante;
	}

	/**
	 * Metodo para actualizar la informacion de un estudiante
	 * 
	 * @param id, estudiante
	 * @return estudiante
	 */
	@Override
	@Transactional
	public Estudiante actualizarEstudiante(Long id, Estudiante estudiante) {

		return estudianteRepositorio.findById(id).map(est -> {
			est.setNombre(estudiante.getNombre());
			est.setEmail(estudiante.getEmail());
			est.setFechaNacimiento(estudiante.getFechaNacimiento());
			est.setCursos(estudiante.getCursos());

			return estudianteRepositorio.save(est);
		}).orElseGet(() -> {
			estudiante.setID(id);
			return estudianteRepositorio.save(estudiante);
		});

	}
	/**
	 * Metodo para eliminar un estudiante
	 * @param id
	 */
	@Override
	public void eliminarEstudiante(Long id) {
		estudianteRepositorio.deleteById(id);
		;

	}
	/**
	 * Metodo para buscar un estudiante
	 * @param id
	 */
	@Override
	public Estudiante buscarEstudiantePorId(Long id) {
		return estudianteRepositorio.findById(id).get();
	}
	/**
	 * Metodo que devuelve una lista de estudiantes
	 * @return List<Estudiante> 
	 */
	@Override
	public List<Estudiante> listarTodosLosEstudiantes() {
		return estudianteRepositorio.findAll();
	}
	/**
	 * Metodo que devuelve una lista de estudiantes filtrada por el nombre
	 * @param nombre
	 * @return List<Estudiante> 
	 */
	@Override
	public List<Estudiante> buscarEstudiantesPorNombre(String nombre) {
		return estudianteRepositorio.findByNombre(nombre);
	}

	/**
	 * Metodo que devuelve un Set de estudiantes filtrado por el curso
	 * @param idCurso
	 * @return Set<Estudiante> 
	 */
	@Override
	public Set<Estudiante> obtenerEstudiantesPorCurso(Long idCurso) {
		return null;
	}
	/**
	 * Metodo que agrega un curso al estudiante
	 * @param estudianteId,idCurso
	 */
	@Override
	@Transactional
	public void agregarCursoAEstudiante(Long estudianteId, Long cursoId) {
		Estudiante estudiante = estudianteRepositorio.findById(estudianteId)
				.orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

		Curso curso = cursoRepositorio.findById(cursoId).orElseThrow(() -> new RuntimeException("Curso no encontrado"));

		estudiante.agregarCursos(curso);
		curso.agregarEstudiante(estudiante);

		estudianteRepositorio.save(estudiante);
		cursoRepositorio.save(curso);

	}

}
