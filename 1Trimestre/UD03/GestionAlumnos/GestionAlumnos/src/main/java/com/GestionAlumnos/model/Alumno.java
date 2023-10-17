package com.GestionAlumnos.model;

import java.util.Objects;

public class Alumno {

	private String nombre;
	private Integer edad;
	private String curso;

	public Alumno() {
		super();
	}

	public Alumno(String nombre, Integer edad, String curso) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.curso = curso;
	}

	public enum Curso {
		Primero, Segundo
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

	@Override
	public int hashCode() {
		return Objects.hash(curso, edad, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return curso == other.curso && Objects.equals(edad, other.edad) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", edad=" + edad + ", curso=" + curso + "]";
	}

}
