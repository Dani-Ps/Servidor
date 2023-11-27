package com.example.demo.servicio;

import java.util.List;
import com.example.demo.entidad.Curso;

public interface CursoServicio {
	Curso guardarCurso(Curso curso);
    void eliminarCurso(Long id);
    List<Curso> listarTodosLosCursos();
    Curso buscarCursoPorId(Long id);
    Curso actualizarCurso(Long id, Curso curso);
    List<Curso> buscarCursosPorTitulo(String titulo);
    List<Curso> buscarPorTituloParcialSQL(String titulo);
}

