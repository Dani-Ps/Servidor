package com.example.demo.entidad;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    @NotBlank(message = "El nombre no puede estar vacío") // Asegura que el nombre no sea null o esté vacío
    @Size(min=1, max = 25, message = "Un mínimo de 1 y máximo de 25 caracteres") // Limita la longitud del nombre
    private String nombre;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Libro> libros = new ArrayList<>();
    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    // Métodos de conveniencia para sincronizar ambos lados de la relación
    public void addLibro(Libro libro) {
        libros.add(libro);
        libro.setAutor(this);
    }

    public void removeLibro(Libro libro) {
        libros.remove(libro);
        libro.setAutor(null);
    }
}