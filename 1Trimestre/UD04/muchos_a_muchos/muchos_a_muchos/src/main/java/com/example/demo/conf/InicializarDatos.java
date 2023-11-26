package com.example.demo.conf;

import com.example.demo.entidad.Curso;
import com.example.demo.entidad.Estudiante;
import com.example.demo.repositorio.CursoRepositorio;
import com.example.demo.repositorio.EstudianteRepositorio;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Random;

@Component
public class InicializarDatos implements CommandLineRunner {

    @Autowired
    private EstudianteRepositorio estudianteRepositorio;

    @Autowired
    private CursoRepositorio cursoRepositorio;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        // Crear y guardar cursos ficticios
        for (int i = 0; i < 10; i++) {
            Curso curso = new Curso();
            curso.setTitulo(faker.educator().course());
            curso.setDescripcion(faker.lorem().sentence());
            // ... otros campos del curso
            cursoRepositorio.save(curso);
        }

        List<Curso> cursosDisponibles = cursoRepositorio.findAll();

        // Crear y guardar estudiantes ficticios
        for (int i = 0; i < 10; i++) {
            Estudiante estudiante = new Estudiante();
            estudiante.setNombre(faker.name().fullName());
            estudiante.setEmail(faker.internet().emailAddress());
            estudiante.setFechaNacimiento(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            // ... otros campos del estudiante

            Random random = new Random();
            int numeroDeCursos = 2 + random.nextInt(2); // 2 o 3 cursos
            Set<Curso> cursosAsignados = new HashSet<>();

            for (int j = 0; j < numeroDeCursos; j++) {
                cursosAsignados.add(cursosDisponibles.get(random.nextInt(cursosDisponibles.size())));
            }

            estudiante.setCursos(cursosAsignados);
            estudianteRepositorio.save(estudiante);
        }
    }
}