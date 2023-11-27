package com.example.demo.entidad;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "estudiantes")
public class Estudiante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	@NotBlank(message = "El nombre no puede estar vacio")
    @Size(max = 50, message = "El nombre debe tener entre 10 y 50 caracteres")
	private String nombre;
	
	@NotNull(message = "El email no puede estat vacio")
	@Email(message = "Debe ser un email")
	private String email;

	@NotNull(message = "message=")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
	private LocalDate fechaNacimiento;

	@ManyToMany
	@JoinTable(name = "estudiante_curso", joinColumns = @JoinColumn(name = "estudiante_id"), inverseJoinColumns = @JoinColumn(name = "curso_id"))
	private Set<Curso> cursos;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
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

	public void agregarCursos(Curso curso) {
		cursos.add(curso);
		
	}
	
	
}
