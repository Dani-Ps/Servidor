package com.example.demo.servicio;

import static org.junit.jupiter.api.Assertions.*;import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entidad.Curso;
import com.example.demo.entidad.Estudiante;

import jakarta.transaction.Transactional;

@SpringBootTest
class EstudianteServicioTest {
	
	@Autowired
    private EstudianteServicio estudianteServicio;

    @Test
    public void cuandoAgregaEstudianteACurso_entoncesEstudianteEstaEnCurso() {
        // Crear y guardar curso a través del servicio
        Curso curso = new Curso();
        curso.setTitulo("Curso de Spring");
        curso = estudianteServicio.guardarCurso(curso); // Asumiendo que el servicio tiene este método

        // Crear y guardar estudiante a través del servicio
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre("Juan Pérez");
        estudiante.setFechaNacimiento(LocalDate.of(2000, 1, 1));
        estudiante = estudianteServicio.guardarEstudiante(estudiante); // Asumiendo que el servicio tiene este método

        // Agregar estudiante al curso a través del servicio
        estudianteServicio.agregarCursoAEstudiante(estudiante.getId(), curso.getId());

        // Recuperar el curso actualizado de la base de datos a través del servicio
        Curso cursoActualizado = estudianteServicio.buscarCursoPorId(curso.getId());

        assertNotNull(cursoActualizado);
        assertTrue(cursoActualizado.getEstudiantes().contains(estudiante));
    }
}
