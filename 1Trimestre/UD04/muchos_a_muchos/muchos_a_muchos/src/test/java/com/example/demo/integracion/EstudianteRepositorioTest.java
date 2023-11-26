package com.example.demo.integracion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entidad.Estudiante;
import com.example.demo.repositorio.EstudianteRepositorio;

@DataJpaTest
class EstudianteRepositorioTest {

	 @Autowired
	 private EstudianteRepositorio estudianteRepositorio;
	 
	 @Test
	 public void cuandoGuarda_thenEncuentraPorNombre() {
	        // Crear una instancia de estudiante
	        Estudiante nuevoEstudiante = new Estudiante();
	        nuevoEstudiante.setNombre("Juan");
	        nuevoEstudiante.setFechaNacimiento(LocalDate.of(2000, 1, 1)); 
	        estudianteRepositorio.save(nuevoEstudiante);

	        // Buscar el estudiante por nombre
	        List<Estudiante> encontrado = estudianteRepositorio.findByNombre("Juan");
	        assertThat(encontrado).first().isNotNull();
	        assertThat(encontrado.get(0).getNombre()).isEqualTo("Juan");
	    }
	 
	 	@Test
	    public void cuandoGuarda_thenEncuentraTodos() {
	        Estudiante estudiante1 = new Estudiante();
	        Estudiante estudiante2 = new Estudiante();
	        estudiante1.setNombre("ANA");
	        estudiante1.setFechaNacimiento(LocalDate.of(2000, 1, 1)); 
	        estudiante2.setNombre("Rocio");
	        estudiante2.setFechaNacimiento(LocalDate.of(2000, 1, 1)); 
	        
	        estudianteRepositorio.save(estudiante1);
	        estudianteRepositorio.save(estudiante2);

	        List<Estudiante> estudiantes = estudianteRepositorio.findAll();
	        assertThat(estudiantes).hasSize(2);
	    }
}
