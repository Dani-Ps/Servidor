/**
 * Interfaz que define las operaciones para el control de estudiantes en el sistema de gestión de alumnos.
 */
package com.GestionAlumnos.control;
import org.springframework.ui.Model;


public interface ControlEstudianteI {

	/**
	 * Recupera la lista de estudiantes y la almacena en el modelo para su
	 * visualización.
	 *
	 * @param model El modelo en el que se almacenará la lista de estudiantes.
	 * @return Una cadena que representa la vista a mostrar.
	 */
	public String listar(Model model);

	/**
	 * Agrega un nuevo estudiante con el nombre, edad y curso especificados.
	 *
	 * @param nombre El nombre del estudiante a agregar.
	 * @param edad   La edad del estudiante a agregar.
	 * @param curso  El curso al que pertenece el estudiante.
	 * @return Una cadena que representa la vista a mostrar después de agregar al
	 *         estudiante.
	 */
	public String añadir(String nombre, Integer edad, String curso);

	/**
	 * Elimina al estudiante con el nombre especificado.
	 *
	 * @param nombre El nombre del estudiante a eliminar.
	 * @return Una cadena que representa la vista a mostrar después de eliminar al
	 *         estudiante.
	 */
	public String eliminar(String nombre);

	/**
	 * Edita la información del estudiante con el nombre especificado y la almacena
	 * en el modelo.
	 *
	 * @param nombre El nombre del estudiante a editar.
	 * @param model  El modelo en el que se almacenará la información del estudiante
	 *               editado.
	 * @return Una cadena que representa la vista a mostrar para editar al
	 *         estudiante.
	 */
	String editar(String nombre, Model model);
}
