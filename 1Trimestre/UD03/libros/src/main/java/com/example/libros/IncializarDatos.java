package com.example.libros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.libros.entidad.Categoria;
import com.example.libros.entidad.Libro;
import com.example.libros.servicio.ServicioLibros;

import jakarta.annotation.PostConstruct;

@Component
public class IncializarDatos {
	
	@Autowired
    private ServicioLibros servicioLibros;

    @PostConstruct
    public void init() {

         Libro l1 = new Libro();
         l1.setId(1L);
         l1.setAutor("Sofía Martínez");
         l1.setNombre("La paz escondida");
         l1.setCategoria(Categoria.FANTASIA);
         l1.setPrecio(3.20);
         servicioLibros.guardarLibro(l1);

         Libro l2 = new Libro();
         l2.setId(2L);
         l2.setAutor("Carlos Luna");
         l2.setNombre("Versos de tranquilidad");
         l2.setCategoria(Categoria.POESIA);
         l2.setPrecio(4.50);  // Precio añadido
         servicioLibros.guardarLibro(l2);

         Libro l3 = new Libro();
         l3.setId(3L);
         l3.setAutor("Elena Castillo");
         l3.setNombre("Amores pacíficos");
         l3.setCategoria(Categoria.ROMANCE);
         l3.setPrecio(5.75);  // Precio añadido
         servicioLibros.guardarLibro(l3);
         Libro l4 = new Libro();
         l4.setId(4L);
         l4.setAutor("Roberto Silva");
         l4.setNombre("El futuro sereno");
         l4.setCategoria(Categoria.CIENCIA_FICCION);
         l4.setPrecio(7.10);  // Precio añadido
         servicioLibros.guardarLibro(l4);

         Libro l5 = new Libro();
         l5.setId(5L);
         l5.setAutor("Ricardo Mora");
         l5.setNombre("Tiempos de armonía");
         l5.setCategoria(Categoria.HISTORIA);
         l5.setPrecio(8.20);  // Precio añadido
         servicioLibros.guardarLibro(l5);

         Libro l6 = new Libro();
         l6.setId(6L);
         l6.setAutor("Patricia Solano");
         l6.setNombre("El refugio pacífico");
         l6.setCategoria(Categoria.FANTASIA);
         l6.setPrecio(9.30);  // Precio añadido
         servicioLibros.guardarLibro(l6);

         Libro l7 = new Libro();
         l7.setId(7L);
         l7.setAutor("David Guzmán");
         l7.setNombre("Cantos de serenidad");
         l7.setCategoria(Categoria.POESIA);
         l7.setPrecio(10.40);  // Precio añadido
         servicioLibros.guardarLibro(l7);

         Libro l8 = new Libro();
         l8.setId(8L);
         l8.setAutor("Lorena Ruiz");
         l8.setNombre("Alianzas en el silencio");
         l8.setCategoria(Categoria.ROMANCE);
         l8.setPrecio(11.50);  // Precio añadido
         servicioLibros.guardarLibro(l8);

         Libro l9 = new Libro();
         l9.setId(9L);
         l9.setAutor("Miguel Torres");
         l9.setNombre("La colonia pacífica");
         l9.setCategoria(Categoria.CIENCIA_FICCION);
         l9.setPrecio(12.60);  // Precio añadido
         servicioLibros.guardarLibro(l9);

         Libro l10 = new Libro();
         l10.setId(10L);
         l10.setAutor("Carmen Linares");
         l10.setNombre("La tregua histórica");
         l10.setCategoria(Categoria.HISTORIA);
         l10.setPrecio(13.70);  // Precio añadido
         servicioLibros.guardarLibro(l10);

         Libro l11 = new Libro();
         l11.setId(11L);
         l11.setAutor("Alejandro Sánchez");
         l11.setNombre("Meditaciones tranquilas");
         l11.setCategoria(Categoria.DIDACTICOS);
         l11.setPrecio(14.80);  // Precio añadido
         servicioLibros.guardarLibro(l11);

         Libro l12 = new Libro();
         l12.setId(12L);
         l12.setAutor("Rosa Méndez");
         l12.setNombre("El mundo sin sombras");
         l12.setCategoria(Categoria.HORROR);
         l12.setPrecio(15.90);  // Precio añadido
         servicioLibros.guardarLibro(l12);

         Libro l13 = new Libro();
         l13.setId(13L);
         l13.setAutor("Hernán Cortés");
         l13.setNombre("El valle sereno");
         l13.setCategoria(Categoria.FANTASIA);
         l13.setPrecio(16.00);  // Precio añadido
         servicioLibros.guardarLibro(l13);

         Libro l14 = new Libro();
         l14.setId(14L);
         l14.setAutor("Susana Paredes");
         l14.setNombre("Poemas del ocaso");
         l14.setCategoria(Categoria.POESIA);
         l14.setPrecio(17.10);  // Precio añadido
         servicioLibros.guardarLibro(l14);

         Libro l15 = new Libro();
         l15.setId(15L);
         l15.setAutor("Roberto Alarcón");
         l15.setNombre("Los amantes del océano pacífico");
         l15.setCategoria(Categoria.ROMANCE);
         l15.setPrecio(18.20);  // Precio añadido
         servicioLibros.guardarLibro(l15);
         
         Libro l16 = new Libro();
         l16.setId(16L);
         l16.setAutor("Gabriela Castro");
         l16.setNombre("Paz intergaláctica");
         l16.setCategoria(Categoria.CIENCIA_FICCION);
         l16.setPrecio(6.90);  // Precio añadido
         servicioLibros.guardarLibro(l16);


    }
}
