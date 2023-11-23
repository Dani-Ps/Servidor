package com.pablo.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pablo.crud.model.Estudiante;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class EstudianteUnitTest {

	private Validator validator;
	
	@BeforeEach
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	void setter_getter() {
		Estudiante estudiante = new Estudiante();
		estudiante.setId(1);
		estudiante.setNombre("Pablo");
		estudiante.setEdad(20);
		estudiante.setCurso("2DAW");
		assertEquals(estudiante.getId(), 1);
		assertEquals(estudiante.getNombre(), "Pablo");
		assertEquals(estudiante.getEdad(), 20);
		assertEquals(estudiante.getCurso(), "2DAW");
	}
	
	@Test
	void whenNombreIsBlank_thenConstraintViolation() {
		Estudiante estudiante = new Estudiante();
		estudiante.setId(1);
		estudiante.setNombre("");
		estudiante.setEdad(20);
		estudiante.setCurso("2DAW");
		
		Set<ConstraintViolation<Estudiante>> violations = validator.validate(estudiante);
		assertFalse(violations.isEmpty());
	}

}
