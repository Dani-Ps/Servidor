package com.example.demo.entidad;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.persistence.JoinColumn;

@Entity
public class Estudiante {
	   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @Email(message = "Correo electrónico no válido")
    private String email;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate fechaNacimiento;

    
	 @ManyToMany
	    @JoinTable(
	        name = "estudiante_curso", 
	        joinColumns = @JoinColumn(name = "estudiante_id"), 
	        inverseJoinColumns = @JoinColumn(name = "curso_id")
	    )
	    private Set<Curso> cursos;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public Set<Curso> getCursos() {
		return cursos;
	}


	public void setCursos(Set<Curso> cursos) {
		this.cursos = cursos;
	}
	 
	public boolean agregarCursos(Curso curso) {
		return cursos.add(curso);
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (obj == null || getClass() != obj.getClass()) {
	        return false;
	    }
	    Estudiante other = (Estudiante) obj;
	    return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(id);
	}
}
