package com.example.libros.controlador;


import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.example.libros.entidad.Categoria;
import com.example.libros.entidad.Libro;
import com.example.libros.servicio.ServicioCategorias;
import com.example.libros.servicio.ServicioLibros;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class ControladorLibro {
	private

	@Autowired ServicioLibros serviciolibros;
	
	@Autowired
	private LocaleResolver localeResolver;


	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("mensaje", " DWES :: RA3");
		return "index";
	}

	@GetMapping("/libros")
	public String listarLibros(Model model) {
		List<Libro> libros = serviciolibros.obtenerLibros();
		model.addAttribute("libros", libros);
		model.addAttribute("categorias", Categoria.values());
		return "libros";
	}

	@GetMapping("/filtrar")
	public String filtrarLibros(@RequestParam String categoria, Model model) {
		List<Libro> libros = serviciolibros.obtenerLibros(categoria);
		model.addAttribute("libros", libros);
		model.addAttribute("categorias", Categoria.values());
		return "libros";
	}

	@GetMapping("/formulario")
	public String form_libro(Model model) {
		model.addAttribute("categorias", Categoria.values());
		model.addAttribute("libro", new Libro());
		return "form_libro";
	}

	@PostMapping("/nuevo_libro")
	public String agregarLibro(@ModelAttribute("libro") Libro libro, BindingResult result) {

		// Validación manual de los campos
		if (libro.getAutor().trim().isEmpty()) {
			result.rejectValue("autor", "error.autor", "El campo autor no puede estar vacío");
		}
		if (libro.getNombre().trim().isEmpty()) {
			result.rejectValue("nombre", "error.nombre", "El campo nombre no puede estar vacío");
		}
		if (libro.getPrecio() == null || libro.getPrecio() <= 0) {
			result.rejectValue("precio", "error.precio", "El precio debe ser un número positivo");
		}
		if (libro.getAnoPublicacion() == null || libro.getAnoPublicacion() <= 0) {
			result.rejectValue("anoPublicacion", "error.anoPublicacion",
					"El año de publicación debe ser un número positivo");
		}

		// Si hay errores, volver al formulario
		if (result.hasErrors()) {
			return "form_libro";
		}

		serviciolibros.guardarLibro(libro);
		return "redirect:/libros"; // redirigir a la página principal
	}

	@PostMapping("/libro")
	public String guardarLibro(@Valid @ModelAttribute("libro") Libro libro, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("categorias", ServicioCategorias.getCategorias());
			return "form_libro";
		}
		serviciolibros.guardarLibro(libro);
		return "redirect:/libros";
	}

	@PostMapping("/cambiarIdioma")
	public String cambiarIdioma(HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "lang", required = false) String lang) {
	    LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);

	    if (localeResolver == null) {
	        throw new IllegalStateException("No se encontró un LocaleResolver: asegúrate de haber configurado uno.");
	    }

	    if (lang == null || lang.isEmpty()) {
	        // Puedes establecer un idioma predeterminado si no se proporciona uno
	        localeResolver.setLocale(request, response, Locale.getDefault());
	    } else {
	        localeResolver.setLocale(request, response, new Locale(lang));
	    }

	    return "redirect:/libros"; // Redirige a la página principal
	}

}
