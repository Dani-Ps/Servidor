package com.example.demo.entidad;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cursos")
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	@NotBlank(message = "El nombre no puede estar vacio")
    @Size(max = 100, message = "La descripción debe tener 100 caracteres")
	private String titulo;
	
    @Size(min = 10, max = 100, message = "La descripción debe tener entre 10 y 200 caracteres")
	private String descripcion;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "cursos")
	private Set<Estudiante> estudiantes;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(Set<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public void agregarEstudiante(Estudiante estudiante) {
		estudiantes.add(estudiante);
	}

	
}
