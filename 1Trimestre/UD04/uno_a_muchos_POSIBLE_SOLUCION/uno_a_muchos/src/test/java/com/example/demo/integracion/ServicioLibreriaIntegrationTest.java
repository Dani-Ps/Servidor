package com.example.demo.integracion;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entidad.Autor;
import com.example.demo.entidad.Libro;
import com.example.demo.enumerado.Categoria;
import com.example.demo.servicio.ServicioLibreria;

import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ServicioLibreriaIntegrationTest {

    @Autowired
    private ServicioLibreria servicioLibreria;

    @Test
    public void testGuardarYObtenerLibro() {
        // Crear y guardar un libro
        Libro libro = new Libro();
        libro.setTitulo("Prueba");
        libro.setCategoria(Categoria.DIDACTICOS);
        Autor autor = new Autor();
        autor.setNombre("Nombre autor");
        libro.setAutor(autor);
        // Configura el resto de los campos necesarios, como autor, categoría, etc.
        libro = servicioLibreria.guardarLibro(libro);

        // Verificar la recuperación del libro
        Libro libroRecuperado = servicioLibreria.obtenerLibroPorId(libro.getId());
        assertEquals(libro.getId(), libroRecuperado.getId());
        assertEquals("Prueba", libroRecuperado.getTitulo());
        // Realiza más aserciones según sea necesario
    }

    @Test
    public void testListarLibros() {
        // Asumiendo que la base de datos ya tiene libros
        List<Libro> libros = servicioLibreria.listarTodosLosLibros();
        assertFalse(libros.isEmpty());
    }

    @Test
    public void testActualizarLibro() {
        // Asumiendo que hay un libro en la base de datos con id conocido
        Long libroId = 1L; // Cambia esto por un ID real de tu base de datos
        Libro libro = servicioLibreria.obtenerLibroPorId(libroId);
        libro.setTitulo("Nuevo Título");
        servicioLibreria.actualizarLibro(libroId, libro);

        Libro libroActualizado = servicioLibreria.obtenerLibroPorId(libroId);
        assertEquals("Nuevo Título", libroActualizado.getTitulo());
    }

    @Test
    public void testEliminarLibro() {
        // Asumiendo que hay un libro en la base de datos con id conocido
        Long libroId = 1L; // Cambia esto por un ID real de tu base de datos
        servicioLibreria.eliminarLibro(libroId);

        assertNull(servicioLibreria.obtenerLibroPorId(libroId));
    }

    @Test
    public void testObtenerAutores() {
        Set<Autor> autores = servicioLibreria.obtenerAutores();
        assertNotNull(autores);
        assertFalse(autores.isEmpty());
    }

    @Test
    public void testObtenerAutor() {
        // Asumiendo que hay un autor en la base de datos con id conocido
        Long autorId = 1L; // Cambia esto por un ID real de tu base de datos
        Autor autor = servicioLibreria.obternerAutor(autorId);
        assertNotNull(autor);
        assertEquals(autorId, autor.getId());
    }
}
