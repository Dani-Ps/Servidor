package com.example.demo.integracion;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entidad.Curso;
import com.example.demo.repositorio.CursoRepositorio;

@DataJpaTest
public class CursoRepositoryTest {
	
	@Autowired
    private CursoRepositorio cursoRepositorio;

    @Test
    public void cuandoGuardaCurso_entoncesRecuperable() {
        Curso curso = new Curso();
        curso.setTitulo("Curso de Spring");
        cursoRepositorio.save(curso);

        Curso encontrado = cursoRepositorio.findById(curso.getId()).orElse(null);
        assertNotNull(encontrado);
    }
}
