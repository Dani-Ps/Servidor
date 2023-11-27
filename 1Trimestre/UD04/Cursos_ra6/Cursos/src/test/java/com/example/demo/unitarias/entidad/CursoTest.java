package com.example.demo.unitarias.entidad;

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
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void tituloNoPuedeEstarVacio() {
        Curso curso = new Curso();
        curso.setTitulo("");
        curso.setDescripcion("Una descripción válida con más de 10 caracteres");

        var violations = validator.validate(curso);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("El título no puede estar vacío")));
    }

    @Test
    public void descripcionLongitudInvalida() {
        Curso curso = new Curso();
        curso.setTitulo("Curso Válido");
        curso.setDescripcion("Corta");

        var violations = validator.validate(curso);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("entre 10 y 200 caracteres")));
    }
    

    @Test
    public void tituloValido() {
        Curso curso = new Curso();
        curso.setTitulo("Curso de Programación");
        curso.setDescripcion("Una descripción válida con más de 10 caracteres");

        var violations = validator.validate(curso);
        assertTrue(violations.isEmpty(), "No debería haber violaciones de validación con un título válido");
    }

    @Test
    public void descripcionValida() {
        Curso curso = new Curso();
        curso.setTitulo("Curso Válido");
        curso.setDescripcion("Esta es una descripción perfectamente válida con longitud adecuada.");

        var violations = validator.validate(curso);
        assertTrue(violations.isEmpty(), "No debería haber violaciones de validación con una descripción válida");
    }

    @Test
    public void descripcionMuyCorta() {
        Curso curso = new Curso();
        curso.setTitulo("Curso Válido");
        curso.setDescripcion("Corta");

        var violations = validator.validate(curso);
        assertFalse(violations.isEmpty(), "Debería haber una violación de validación por descripción corta");
    }

    @Test
    public void descripcionMuyLarga() {
        Curso curso = new Curso();
        curso.setTitulo("Curso Válido");
        String descripcionLarga = "a".repeat(201); // Crea una cadena de 201 caracteres
        curso.setDescripcion(descripcionLarga);

        var violations = validator.validate(curso);
        assertFalse(violations.isEmpty(), "Debería haber una violación de validación por descripción larga");
    }

    @Test
    public void todosLosCamposValidos() {
        Curso curso = new Curso();
        curso.setTitulo("Curso de Matemáticas");
        curso.setDescripcion("Descripción válida de longitud adecuada para el curso de matemáticas.");

        var violations = validator.validate(curso);
        assertTrue(violations.isEmpty(), "No debería haber violaciones de validación cuando todos los campos son válidos");
    }

}
