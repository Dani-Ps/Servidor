package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidad.Libro;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {
}