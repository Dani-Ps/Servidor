package com.example.demo.servicio;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Autor;
import com.example.demo.entidad.Libro;
import com.example.demo.repositorio.AutorRepositorio;
import com.example.demo.repositorio.LibroRepositorio;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class ServicioLibreriaImpl implements ServicioLibreria {
	
  
    @Autowired
    private LibroRepositorio libroRepositorio;
    
    @Autowired
    private AutorRepositorio autorRepositorio;


    @Override
    @Transactional
    public Libro guardarLibro(Libro libro) {
        return libroRepositorio.save(libro);
    }

    @Override
    @Transactional
    public void eliminarLibro(Long id) {
        libroRepositorio.deleteById(id);
    }

    @Override
    @Transactional
    public Libro obtenerLibroPorId(Long id) {
        return libroRepositorio.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public List<Libro> listarTodosLosLibros() {
        return libroRepositorio.findAll();
    }

    @Override
    @Transactional
    public Libro actualizarLibro(Long id, Libro libro) {
        return libroRepositorio.findById(id)
                .map(libroExistente -> {
                    libroExistente.setTitulo(libro.getTitulo());
                    libroExistente.setAutor(libro.getAutor());
                    libroExistente.setCategoria(libro.getCategoria());
                    return libroRepositorio.save(libroExistente);
                }).orElseGet(() -> {
                    libro.setId(id);
                    return libroRepositorio.save(libro);
                });
    }

	@Override
	public Set<Autor> obtenerAutores() {
		return  new HashSet<>( autorRepositorio.findAll());
	}

	@Override
	public Autor obternerAutor(Long id) {
		return autorRepositorio.findById(id).orElse(null);
	}

	@Override
	public void guardarAutor(@Valid Autor autor) {
		autorRepositorio.save(autor);
		
	}
}


