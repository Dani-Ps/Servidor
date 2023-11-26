package com.example.demo.unitaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entidad.Autor;
import com.example.demo.entidad.Libro;
import com.example.demo.repositorio.AutorRepositorio;
import com.example.demo.repositorio.LibroRepositorio;
import com.example.demo.servicio.ServicioLibreriaImpl;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ServicioLibreriaTest {

    @Mock
    private LibroRepositorio libroRepositorio;
    @Mock
    private AutorRepositorio autorRepositorio;

    @InjectMocks
    private ServicioLibreriaImpl servicioLibreria;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarLibro() {
        Libro libro = new Libro();
        when(libroRepositorio.save(any(Libro.class))).thenReturn(libro);

        Libro resultado = servicioLibreria.guardarLibro(libro);

        assertNotNull(resultado);
        verify(libroRepositorio).save(libro);
    }

    @Test
    void testEliminarLibro() {
        Long libroId = 1L;
        doNothing().when(libroRepositorio).deleteById(libroId);

        servicioLibreria.eliminarLibro(libroId);

        verify(libroRepositorio).deleteById(libroId);
    }

    @Test
    void testObtenerLibroPorId() {
        Long libroId = 1L;
        Libro libro = new Libro();
        libro.setId(libroId);
        when(libroRepositorio.findById(libroId)).thenReturn(Optional.of(libro));

        Libro resultado = servicioLibreria.obtenerLibroPorId(libroId);

        assertEquals(libroId, resultado.getId());
        verify(libroRepositorio).findById(libroId);
    }

    @Test
    void testListarTodosLosLibros() {
        when(libroRepositorio.findAll()).thenReturn(Arrays.asList(new Libro(), new Libro()));

        List<Libro> libros = servicioLibreria.listarTodosLosLibros();

        assertNotNull(libros);
        assertEquals(2, libros.size());
        verify(libroRepositorio).findAll();
    }

    @Test
    void testActualizarLibro() {
        Long libroId = 1L;
        Libro libro = new Libro();
        libro.setId(libroId);
        libro.setTitulo("Original");

        when(libroRepositorio.findById(libroId)).thenReturn(Optional.of(libro));
        when(libroRepositorio.save(any(Libro.class))).thenReturn(libro);

        libro.setTitulo("Actualizado");
        Libro resultado = servicioLibreria.actualizarLibro(libroId, libro);

        assertEquals("Actualizado", resultado.getTitulo());
        verify(libroRepositorio).findById(libroId);
        verify(libroRepositorio).save(libro);
    }

    @Test
    void testObtenerAutores() {
        when(autorRepositorio.findAll()).thenReturn(Arrays.asList(new Autor(), new Autor()));

        Set<Autor> autores = servicioLibreria.obtenerAutores();

        assertNotNull(autores);
        assertEquals(2, autores.size());
        verify(autorRepositorio).findAll();
    }

    @Test
    void testObtenerAutor() {
        Long autorId = 1L;
        Autor autor = new Autor();
        autor.setId(autorId);
        when(autorRepositorio.findById(autorId)).thenReturn(Optional.of(autor));

        Autor resultado = servicioLibreria.obternerAutor(autorId);

        assertEquals(autorId, resultado.getId());
        verify(autorRepositorio).findById(autorId);
    }
}
