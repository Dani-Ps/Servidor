package com.pablo.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pablo.crud.model.Estudiante;
import com.pablo.crud.repository.EstudianteRepository;

/**
 * Servicio de estudiantes
 */
@Service
public class EstudianteService {
	
	/**
	 * Inyeccion de el repositorio de estudiantes
	 */
	@Autowired
	private EstudianteRepository estudianteRepository;

	/**
	 * Metodo para conseguir todos los estudiantes
	 * 
	 * @return todos los estudiantes
	 */
	public List<Estudiante> listarEstudiantes(){
		return (List<Estudiante>) estudianteRepository.findAll();
	}
	
	/**
	 * Metodo para guardar un estudiante
	 * 
	 * @return el estudiante creado
	 */
	public Estudiante guardarEstudiante(Estudiante estudiante){
		return estudianteRepository.save(estudiante);
	}

	/**
	 * Metodo para encontrar un estudiante por id
	 * 
	 * @param id
	 * @return el estudiante 
	 */
	public Estudiante encontrarPorId(Integer id) {
		return estudianteRepository.findById(id).get();
	}
	/**
	 * Metodo para eliminar un estudiante por id
	 * 
	 * @param id
	 */
	public void eliminarEstudiantePorId(Integer id) {
		estudianteRepository.deleteById(id);
	}
}
