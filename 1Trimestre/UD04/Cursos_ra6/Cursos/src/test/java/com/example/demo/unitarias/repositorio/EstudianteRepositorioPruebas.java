package com.example.demo.unitarias.repositorio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entidad.Estudiante;
import com.example.demo.repositorio.EstudianteRepositorio;

@DataJpaTest
class EstudianteRepositorioPruebas {

	@Autowired
	private EstudianteRepositorio estudianteRepositorio;
	// Test para verificar el encontrar y el guardar
	@Test
	public void cuandoGuardayEncuentraPorNombre() {

		Estudiante nuevoEstudiante = new Estudiante();
		nuevoEstudiante.setNombre("Dani");
		nuevoEstudiante.setFechaNacimiento(LocalDate.of(2000, 1, 1));
		estudianteRepositorio.save(nuevoEstudiante);

		List<Estudiante> encontrado = estudianteRepositorio.findByNombre("Dani");
		assertThat(encontrado).first().isNotNull();
		assertThat(encontrado.get(0).getNombre()).isEqualTo("Dani");
	}
	// Test para verificar el encontrar y el guardar

	@Test
	public void cuandoGuardayEncuentraTodos() {
		Estudiante estudiante1 = new Estudiante();
		Estudiante estudiante2 = new Estudiante();
		estudiante1.setNombre("FRANCISCO");
		estudiante1.setFechaNacimiento(LocalDate.of(2000, 1, 1));
		estudiante2.setNombre("PEPE");
		estudiante2.setFechaNacimiento(LocalDate.of(2000, 1, 1));

		estudianteRepositorio.save(estudiante1);
		estudianteRepositorio.save(estudiante2);

		List<Estudiante> estudiantes = estudianteRepositorio.findAll();
		assertThat(estudiantes).hasSize(2);
	}

	@Test
	public void pruebaBuscarEstudiantePorNombreNoExistente() {
		List<Estudiante> estudiantesEncontrado = estudianteRepositorio.findByNombre("Nombre no existente");

		assertTrue(estudiantesEncontrado.isEmpty());

	}
	
	
}
