package com.example.demo.unitarias.repositorio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entidad.Curso;
import com.example.demo.repositorio.CursoRepositorio;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CursoRepositorioPruebas {

    @Autowired
    private CursoRepositorio cursoRepositorio;

    @Test
    public void pruebaBuscarCursoPorTitulo() {
        // Crea un curso de prueba
        Curso curso = new Curso();
        curso.setTitulo("Curso de Prueba");
        curso.setDescripcion("Descripción del curso de prueba");
        cursoRepositorio.save(curso);

        // Busca el curso por título
        List<Curso> cursosEncontrados = cursoRepositorio.findByTitulo("Curso de Prueba");

        // Verifica que se haya encontrado al menos un curso
        assertTrue(!cursosEncontrados.isEmpty());

        // Verifica que el título del curso coincida
        assertEquals("Curso de Prueba", cursosEncontrados.get(0).getTitulo());
    }
    @Test
    public void pruebaBuscarCursoPorTituloParcialSQL() {
        // Crea un curso de prueba
        Curso curso = new Curso();
        curso.setTitulo("Curso SQL Prueba");
        curso.setDescripcion("Descripción del curso de prueba");
        cursoRepositorio.save(curso);

        // Busca el curso por título parcial usando SQL nativo
        List<Curso> cursosEncontrados = cursoRepositorio.buscarPorTituloParcialSQL("SQL");

        // Verifica que se haya encontrado al menos un curso
        assertTrue(!cursosEncontrados.isEmpty());

        // Verifica que el título del curso coincida
        assertTrue(cursosEncontrados.get(0).getTitulo().contains("SQL"));
    }

    @Test
    public void pruebaBuscarCursoPorTituloNoExistente() {
        // Busca un curso por un título que no existe en la base de datos
        List<Curso> cursosEncontrados = cursoRepositorio.findByTitulo("Título No Existente");

        // Verifica que no se haya encontrado ningún curso
        assertTrue(cursosEncontrados.isEmpty());
    }

    @Test
    public void pruebaBuscarCursoPorTituloParcialSQLNoExistente() {
        // Busca un curso por un título parcial que no existe en la base de datos
        List<Curso> cursosEncontrados = cursoRepositorio.buscarPorTituloParcialSQL("Título No Existente");

        // Verifica que no se haya encontrado ningún curso
        assertTrue(cursosEncontrados.isEmpty());
    }
}
