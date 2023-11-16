package com.example.mvc_coches.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mvc_coches.modelo.Coche;
import com.example.mvc_coches.servicio.CocheServicio;

import jakarta.validation.Valid;

@Controller
public class CocheControlador {

	@Autowired
	private CocheServicio servicio;
	 
	/**
	 * Maneja la solicitud GET para obtener y mostrar todos los coches en la vista.
	 * @param model El modelo que se pasará a la vista.
	 * @return La vista 'coches' con la lista de todos los coches.
	 */
	@GetMapping("/coches")
	 public String listadoCoches(Model model) {
		 List<Coche> coches = servicio.obtenerTodoslosCoches();
		 model.addAttribute("coches",coches);

		 return "coches";
	 }
	 
	 
	/**
	 * Maneja la solicitud GET para mostrar el formulario de creación de un nuevo coche.
	 * @param model El modelo que se pasará a la vista, conteniendo un nuevo objeto Coche.
	 * @return La vista 'form' para crear un nuevo coche.
	 */
	@GetMapping("/coche/nuevo")
	public String nuevoCoche(Model model) {
		model.addAttribute("coche", new Coche());
		return "form";
	}

	/**
	 * Maneja la solicitud POST para agregar un nuevo coche o actualizar uno existente.
	 * @param model El modelo que se pasará a la vista.
	 * @param coche El objeto Coche que se va a agregar o actualizar.
	 * @param result El resultado de la validación del objeto Coche.
	 * @return Redirige a la vista 'coches' si el coche es válido o retorna a la vista 'form' si hay errores.
	 */
	@PostMapping("/agregar")
	public String agregar( Model model, @ModelAttribute("coche") @Valid Coche coche, BindingResult result) {
		if (result.hasErrors()) {

			return "form";
		}
		servicio.guardar(coche);
		return "redirect:/coches";
	}
	
	/**
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	 @GetMapping("/coche/editar/{id}")
	    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
		 	Coche coche = servicio.obtenerCochePorId(id);
	        model.addAttribute("coche", coche);
	        return "form"; 
	    }

	 /**
	  * Maneja la solicitud GET para mostrar el formulario de edición de un coche existente.
	  * @param id El ID del coche a editar.
	  * @param model El modelo que se pasará a la vista, conteniendo el coche a editar.
	  * @return La vista 'form' con el objeto Coche a editar.
	  */
	    @GetMapping("/coche/eliminar/{id}")
	    public String eliminarProducto(@PathVariable Integer id) {
	        servicio.eliminarProducto(id);
	        return "redirect:/coches";
	    }

}
