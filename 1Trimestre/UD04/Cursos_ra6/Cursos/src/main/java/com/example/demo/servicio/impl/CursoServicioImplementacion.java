package com.example.demo.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Curso;
import com.example.demo.repositorio.CursoRepositorio;
import com.example.demo.servicio.CursoServicio;

@Service
public class CursoServicioImplementacion implements CursoServicio {
	@Autowired
	CursoRepositorio cursoRepositorio;

	/**
	 * Metodo que gurada un curso
	 * @param curso
	 * @return curso
	 */
	@Override
	public Curso guardarCurso(Curso curso) {
		return cursoRepositorio.save(curso);
	}
	/**
	 * Metodo que elimina un curso
	 * @param id
	 */
	@Override
	public void eliminarCurso(Long id) {
		cursoRepositorio.deleteById(id);
	}
	/**
	 * Metodo que devuelve una lista con todos los cursos
	 * @return List<Curso>
	 */
	@Override
	public List<Curso> listarTodosLosCursos() {
		return cursoRepositorio.findAll();
	}

	/**
	 * Metodo que devuelve un curso
	 * @param id
	 * @return curso
	 */
	@Override
	public Curso buscarCursoPorId(Long id) {
		return cursoRepositorio.findById(id).get();
	}

	/**
	 * Metodo que actualiza la info de un curso
	 * @param id, curso
	 * @return curso
	 */
	@Override
	public Curso actualizarCurso(Long id, Curso curso) {
		return cursoRepositorio.findById(id).map(cursoExistente -> {
			cursoExistente.setTitulo(curso.getTitulo());
			cursoExistente.setDescripcion(curso.getDescripcion());
			cursoExistente.setEstudiantes(curso.getEstudiantes());
			
			return cursoRepositorio.save(cursoExistente);
			
		}).orElseGet(() -> {
			curso.setID(id);
            return cursoRepositorio.save(curso);
        });
	}

	/**
	 * Metodo que devuelve una lista de cursos filtradas por titulo
	 * @param titulo
	 * return List<Curso>
	 */
	@Override
	public List<Curso> buscarCursosPorTitulo(String titulo) {
		return buscarCursosPorTitulo(titulo);
	}

	/**
	 * Metodo que devuelve una lista de cursos filtradas por titulo
	 * @param titulo
	 * return List<Curso>
	 */
	@Override
	public List<Curso> buscarPorTituloParcialSQL(String titulo) {
		return buscarPorTituloParcialSQL(titulo);
	}

}
