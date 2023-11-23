package com.pablo.crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Estudiante {
	
	/**
	 * Id del estudiante generado automaticamente
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * Nombre del estudiante, no puede estar vacio
	 */
	@NotBlank(message="El nombre no puede estar vacio")
	private String nombre;
	
	/**
	 * Edad del estudiante, no puede estar vacia
	 */
	@NotNull(message="La edad no puede estar vacia")
	private Integer edad;
	
	/**
	 * Cursop del estudiante, no puede estar vacio
	 */
	@NotBlank(message="El curso no puede estar vacio")
	private String curso;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	

}
