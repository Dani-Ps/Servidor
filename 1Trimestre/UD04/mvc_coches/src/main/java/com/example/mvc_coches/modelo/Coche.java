package com.example.mvc_coches.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Entidad que representa un coche en la base de datos.
 * Contiene las propiedades básicas de un coche y utiliza anotaciones
 * para la validación de sus campos.
 */
@Entity
public class Coche {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @NotBlank(message = "La marca no puede estar vacía")
    @Size(max = 50, message = "La marca no puede tener más de 50 caracteres")
    private String marca;
    
    @NotBlank(message = "La matrícula no puede estar vacía")
    @Size(min = 7, max = 7, message = "La matrícula debe tener 7 caracteres")
    private String matricula;
    
    @NotBlank(message = "El color no puede estar vacío")
    private String color;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


    
}