package com.example.libros.entidad;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Libro {

    private Long id;
    
    @NotBlank(message = "{autor.notblank}")
    @Size(min = 1, max = 100, message = "{autor.size}")
    private String autor;
    
    @NotBlank(message = "{nombre.notblank}")
    @Size(min = 1, max = 100, message = "{nombre.size}")
	    private String nombre;
	    
    @NotBlank(message = "{precio.notblank}")
    @Positive(message = "{precio.positive}")
    private Double precio;
    
    @NotNull(message = "{anoPublicacion.notblank}")
    private Integer anoPublicacion;
    
    @NotNull(message = "{categoria.notblank}")
    private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getAnoPublicacion() {
		return anoPublicacion;
	}

	public void setAnoPublicacion(Integer anoPublicacion) {
		this.anoPublicacion = anoPublicacion;
	}

}
