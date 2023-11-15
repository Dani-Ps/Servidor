package com.example.coches.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.coches.entidad.Coche;
import com.example.coches.entidad.FuenteEnergia;
import com.example.coches.entidad.Idioma;
import com.example.coches.servicios.ServicioCoches;

@Controller
public class ControladorCoche {

	@Autowired
	private ServicioCoches servicioCoches;

	/**
	 * Método que lleva a la página principal de la app.
	 * 
	 * @param model
	 * @return devuelve el templade de 'index'
	 */
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("mensaje", " DWES :: RA3");
		model.addAttribute("idiomas", Idioma.values());
		return "index";
	}

	/**
	 * Método que muestra todos los coches de la lista y la posibilidad de filtrar,
	 * cambiar idioma o añadir un nuevo coche.
	 * 
	 * @param model
	 * @return devuelve el template de coches.html
	 */
	@GetMapping("/coches")
	public String mostrarCoches(Model model) {
		List<Coche> coches = servicioCoches.obtenerTodos();
		FuenteEnergia[] fuentes = servicioCoches.obtenerFuentesEnergia();
		model.addAttribute("idiomas", Idioma.values());
		model.addAttribute("coches", coches);
		model.addAttribute("fuenteEnergia", fuentes);
		return "coches";
	}

	/**
	 * Método que muestra todos los coches filtrado por su fuente de energía.
	 * 
	 * @param fuenteEnergia
	 * @param model
	 * @return devuelve el template de coches.html
	 */

	@GetMapping("/filtrar")
	public String filtrarCoches(@RequestParam(name = "fuenteEnergia", required = false) FuenteEnergia fuenteEnergia,
			Model model) {
		model.addAttribute("idiomas", Idioma.values());

		if (fuenteEnergia != null) {
			List<Coche> cochesFiltrados = servicioCoches.filtrarFuenteEnergia(fuenteEnergia);
			model.addAttribute("coches", cochesFiltrados);

		} else {
			List<Coche> coches = servicioCoches.obtenerTodos();
			model.addAttribute("coches", coches);
		}

		FuenteEnergia[] fuentes = servicioCoches.obtenerFuentesEnergia();
		model.addAttribute("fuenteEnergia", fuentes);
		return "coches";
	}

	/**
	 * Método que lleva al formulario para añadir un nuevo coche.
	 * 
	 * @param model
	 * @return devuelve el template de formulario.
	 */
	@GetMapping("/formulario")
	public String formularioCoche(Model model) {
		FuenteEnergia[] fuentes = servicioCoches.obtenerFuentesEnergia();
		model.addAttribute("fuenteEnergia", fuentes);
		model.addAttribute("coche", new Coche());
		return "formulario";
	}

	/**
	 * Método que recoge el nuevo coche que se crea en el formulario, valida sus
	 * datos y lo guarda en la lista de coches
	 * 
	 * @param coche
	 * @param result
	 * @return al template de coches donde se muestra la lista completa de coches y
	 *         demás opciones.
	 */
	@PostMapping("/nuevo_coche")
	public String addCoche(@ModelAttribute("coche") Coche coche, BindingResult result) {

		// Validación manual de los campos
		if (coche.getMarca().trim().isEmpty()) {
			result.rejectValue("marca", "error.marca", "El campo autor no puede estar vacío");
		}
		if (coche.getModelo().trim().isEmpty()) {
			result.rejectValue("modelo", "error.modelo", "El campo modelo no puede estar vacío");
		}
		if (coche.getAno() == null || coche.getAno() <= 0) {
			result.rejectValue("ano", "error.ano", "El año debe ser un número positivo");
		}
		if (coche.getPrecio() == null || coche.getPrecio() <= 0) {
			result.rejectValue("precio", "error.preicio", "El precio debe ser un número positivo");
		}

		// Si hay errores, volver al formulario
		if (result.hasErrors()) {
			return "formulario";
		}
		servicioCoches.guardar(coche);
		return "redirect:/coches";
	}

	/**
	 * Método para cambiar el idioma de las cabeceras de la lista
	 * 
	 * @param idioma
	 * @param model
	 * @return devuelve la pagina principal con el idioma cambiado.
	 */
	@GetMapping("/idioma")
	public String idioma(@RequestParam (name="idiomas", required = false)Idioma idiomas, Model model) {
		model.addAttribute("idiomas", Idioma.values());

		if (idiomas.equals(Idioma.EN)) {
			return "redirect:/?lang=en";
		} else {
			return "redirect:/?lang=es";
		}
	}

}
