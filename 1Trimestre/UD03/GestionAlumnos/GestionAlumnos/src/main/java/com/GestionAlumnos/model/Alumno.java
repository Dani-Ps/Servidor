/**
 * Clase que representa a un estudiante (alumno) en el sistema de gestión de alumnos.
 */
package com.GestionAlumnos.model;

import java.util.Objects;

public class Alumno {

	private String nombre;
	private Integer edad;
	private String curso;

	/**
	 * Constructor sin argumentos de la clase Alumno.
	 */
	public Alumno() {
		super();
	}

	/**
	 * Constructor de la clase Alumno que toma el nombre, edad y curso del
	 * estudiante.
	 *
	 * @param nombre Nombre del estudiante.
	 * @param edad   Edad del estudiante.
	 * @param curso  Curso al que pertenece el estudiante.
	 */
	public Alumno(String nombre, Integer edad, String curso) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.curso = curso;
	}

	/**
	 * Enumeración que representa los posibles cursos a los que puede pertenecer un
	 * estudiante.
	 */
	public enum Curso {
		Primero, Segundo
	}

	/**
	 * Obtiene el nombre del estudiante.
	 *
	 * @return El nombre del estudiante.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del estudiante.
	 *
	 * @param nombre El nombre a establecer.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene la edad del estudiante.
	 *
	 * @return La edad del estudiante.
	 */
	public Integer getEdad() {
		return edad;
	}

	/**
	 * Establece la edad del estudiante.
	 *
	 * @param edad La edad a establecer.
	 */
	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	/**
	 * Obtiene el curso al que pertenece el estudiante.
	 *
	 * @return El curso del estudiante.
	 */
	public String getCurso() {
		return curso;
	}

	/**
	 * Establece el curso al que pertenece el estudiante.
	 *
	 * @param curso El curso a establecer.
	 */
	public void setCurso(String curso) {
		this.curso = curso;
	}

	/**
	 * Calcula el valor hash de la instancia de Alumno basado en nombre, edad y
	 * curso.
	 *
	 * @return El valor hash calculado.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(curso, edad, nombre);
	}

	/**
	 * Compara esta instancia de Alumno con otro objeto para verificar si son
	 * iguales.
	 *
	 * @param obj El objeto a comparar.
	 * @return Verdadero si los objetos son iguales, falso en caso contrario.
	 */
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

	/**
	 * Representa la instancia de Alumno como una cadena de caracteres.
	 *
	 * @return Una cadena que contiene el nombre, edad y curso del estudiante.
	 */
	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", edad=" + edad + ", curso=" + curso + "]";
	}
}
