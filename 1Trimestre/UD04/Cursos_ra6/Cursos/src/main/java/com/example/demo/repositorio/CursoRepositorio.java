package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Curso;

@Repository
public interface CursoRepositorio extends JpaRepository<Curso, Long> {
	// Método para encontrar cursos por su título
    List<Curso> findByTitulo(String titulo);
    
    @Query(value = "SELECT * FROM cursos WHERE titulo LIKE %?1%", nativeQuery = true)
    List<Curso> buscarPorTituloParcialSQL(String titulo);

}
