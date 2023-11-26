package com.example.demo.unitaria;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.demo.entidad.Curso;

public class CursoTest {
	
    private static Validator validator;
    

    @BeforeAll
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void cuandoTituloEsVacio_entoncesValidacionFalla() {
        Curso curso = new Curso();
        curso.setTitulo("");
        curso.setDescripcion("Descripción válida");

        var constraintViolations = validator.validate(curso);

        assertFalse(constraintViolations.isEmpty());
    }

    @Test
    public void cuandoTituloEsValido_entoncesValidacionPasa() {
        Curso curso = new Curso();
        curso.setTitulo("Curso de Spring");
        curso.setDescripcion("Descripción válida");

        var constraintViolations = validator.validate(curso);

        assertTrue(constraintViolations.isEmpty());
    }
}
