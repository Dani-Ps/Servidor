package com.example.demo.entidad;

import com.example.demo.enumerado.Categoria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    @NotBlank(message = "El título no puede estar vacío") // Asegura que el título no sea null o esté vacío
    @Size(min=2, max = 200, message = "El título debe tener entre 2 y 200 caracteres") // Limita la longitud del título
    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    @NotNull(message = "El autor es obligatorio") // Asegura que el libro tenga un autor asociado
    private Autor autor;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "La categoria es obligatoria")
    @Column(name = "categoria", nullable = false) // Especifica detalles de la columna "estado"
    private Categoria categoria;


    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
    
}