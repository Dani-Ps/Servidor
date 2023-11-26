package com.example.demo.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;





import com.example.demo.entidad.Autor;

public interface AutorRepositorio extends JpaRepository<Autor, Long> {

}
