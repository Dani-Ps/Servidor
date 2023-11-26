package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entidad.Autor;
import com.example.demo.entidad.Libro;
import com.example.demo.enumerado.Categoria;
import com.example.demo.servicio.ServicioLibreria;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/libros")
public class LibroControlador {
	
	@Autowired
	private ServicioLibreria servicioLibreria;
	 /**
     * Muestra el listado de todos los libros.
     * @param model
     * @return la vista de lista de libros
     */
    @GetMapping
    String listadoLibros(Model model) {
        model.addAttribute("libros", servicioLibreria.listarTodosLosLibros());
        return "libros/lista";
    }

    /**
     * Muestra el formulario para crear un nuevo libro.
     * @param model
     * @return la vista del formulario de libros
     */
    @GetMapping("/nuevo")
    String nuevoLibro(Model model) {
        model.addAttribute("libro", new Libro());
        model.addAttribute("categorias", Categoria.values());
        model.addAttribute("autores", servicioLibreria.obtenerAutores());
        return "libros/form";
    }

    /**
     * Guarda un nuevo libro.
     * @param libro Libro a guardar
     * @param result Resultado de la validación
     * @return redirecciona al listado de libros
     */
    @PostMapping
    public String guardarLibro(Model model, @Valid @ModelAttribute("libro") Libro libro, BindingResult result) {
        if (result.hasErrors()) {
        	 model.addAttribute("categorias", Categoria.values());
             model.addAttribute("autores", servicioLibreria.obtenerAutores());
            return "libros/form";
        }
        servicioLibreria.guardarLibro(libro);
        return "redirect:/libros";
    }

    /**
     * Muestra el formulario para editar un libro existente.
     * @param id Identificador del libro
     * @param model
     * @return la vista del formulario de libros
     */
    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditarLibro(@PathVariable Long id, Model model) {
        Libro libro = servicioLibreria.obtenerLibroPorId(id);
        model.addAttribute("categorias", Categoria.values());
        model.addAttribute("autores", servicioLibreria.obtenerAutores());
        model.addAttribute("libro", libro);
        return "libros/form";
    }

    /**
     * Actualiza un libro existente.
     * @param id Identificador del libro
     * @param libro Libro con los datos actualizados
     * @param result Resultado de la validación
     * @return redirecciona al listado de libros
     */
    @PostMapping("/{id}")
    public String actualizarLibro(Model model, @PathVariable Long id, @Valid @ModelAttribute("libro") Libro libro, BindingResult result) {
        if (result.hasErrors()) {
        	 model.addAttribute("categorias", Categoria.values());
             model.addAttribute("autores", servicioLibreria.obtenerAutores());
            return "libros/form";
        }
        servicioLibreria.actualizarLibro(id, libro);
        return "redirect:/libros";
    }

    /**
     * Elimina un libro.
     * @param id Identificador del libro a eliminar
     * @return redirecciona al listado de libros
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Long id) {
        servicioLibreria.eliminarLibro(id);
        return "redirect:/libros";
    }
    /**
     * Obtiene la información detallada de un autor específico.
     * @param id Identificador del autor
     * @param model Modelo para pasar datos a la vista
     * @return la vista con la información del autor
     */
    @GetMapping("/autores/{id}")
    public String obtenerInformacionAutor(@PathVariable Long id, Model model) {
        Autor autor = servicioLibreria.obternerAutor(id);
        model.addAttribute("autor", autor);
        // Aquí puedes añadir la lista de libros del autor si es necesario
        // model.addAttribute("libros", servicioLibreria.obtenerLibrosPorAutorId(id));
        return "autor/info";
    }

    /**
     * Muestra el formulario para crear un nuevo autor.
     * @param model Modelo para pasar datos a la vista
     * @return la vista del formulario de autores
     */
    @GetMapping("/autores/nuevo")
    public String nuevoFormAutor(Model model) {
        model.addAttribute("autor", new Autor());
        return "autor/form";
    }
    
    @PostMapping("/autores/nuevo")
    public String nuevoAutor(Model model, @Valid @ModelAttribute("autor") Autor autor, BindingResult result) {
    	if (result.hasErrors()) {
           return "autor/form";
       }
       servicioLibreria.guardarAutor(autor);
       
       return "redirect:/libros/autores/nuevo";
    }
}
