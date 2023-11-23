package com.pablo.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pablo.crud.model.Estudiante;
/**
 * Repositorio de estudiantes
 */
@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer>{

}
