package com.example.demo.entidad;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Curso {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotBlank(message = "El título no puede estar vacío")
	    private String titulo;

	    @Size(min = 10, max = 200, message = "La descripción debe tener entre 10 y 200 caracteres")
	    private String descripcion;
	    
	    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "cursos")
	    private Set<Estudiante> estudiantes = new HashSet<>();

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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
		public boolean agregarEstudiante(Estudiante estudiante) {
			return this.estudiantes.add(estudiante);
		}
	    
	    

}
