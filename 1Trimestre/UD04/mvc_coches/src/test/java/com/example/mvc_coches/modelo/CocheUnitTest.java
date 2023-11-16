package com.example.mvc_coches.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CocheUnitTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


	@Test
	void crearValoresCorrectoProducto() {
		 Coche coche = new Coche();
	        coche.setMarca("Renault");  
	        coche.setMatricula("1234567");
	        coche.setColor("Rojo");

        assertEquals("Renault", coche.getMarca(), "La Marca no coincide");
        assertEquals("1234567", coche.getMatricula(), "La Matricula no coincide");
        assertEquals("Rojo", coche.getColor(), "El color no coincide");
	}
    @Test
    public void whenMarcaIsBlank_thenConstraintViolation() {
        Coche coche = new Coche();
        coche.setMarca(""); // En blanco para probar la validación
        coche.setMatricula("1234567");
        coche.setColor("Rojo");

        Set<ConstraintViolation<Coche>> violations = validator.validate(coche);
        assertFalse(violations.isEmpty()); // Debería fallar marca esta en blanco
    }
    
    @Test
    public void whenMarcaANDMatriculaIsBlank_thenConstraintViolation() {
        Coche coche = new Coche();
        coche.setMarca(""); 
        coche.setMatricula("");
        coche.setColor("Rojo");

        Set<ConstraintViolation<Coche>> violations = validator.validate(coche);
        System.out.println("#####################");
        
        for (ConstraintViolation<Coche> violation : violations) {
			System.out.println(violation);
		}
        assertFalse(violations.isEmpty()); // Debería fallar porque la marca está en blanco
    }

    @Test
    public void whenMatriculaHasIncorrectSize_thenConstraintViolation() {
        Coche coche = new Coche();
        coche.setMarca("Toyota");
        coche.setMatricula("123"); // Tamaño incorrecto para la matrícula
        coche.setColor("Rojo");

        Set<ConstraintViolation<Coche>> violations = validator.validate(coche);
        assertFalse(violations.isEmpty()); // Debería fallar porque la matrícula no tiene 7 caracteres
    }

    @Test
    public void whenCocheIsValid_thenNoConstraintViolations() {
        Coche coche = new Coche();
        coche.setMarca("Toyota");
        coche.setMatricula("ABC1234");
        coche.setColor("Rojo");

        Set<ConstraintViolation<Coche>> violations = validator.validate(coche);
        assertTrue(violations.isEmpty()); // No debería haber violaciones
    }

}
