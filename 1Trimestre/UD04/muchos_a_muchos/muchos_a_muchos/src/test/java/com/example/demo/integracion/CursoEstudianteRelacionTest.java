package com.example.demo.integracion;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entidad.Curso;
import com.example.demo.entidad.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@DataJpaTest
public class CursoEstudianteRelacionTest {
	
	 	@Autowired
	    private EntityManager entityManager;

	    private Curso curso1;
	    private Estudiante estudiante1;

	    @BeforeEach
	    void setUp() {
	        // Inicializar entidades y relación @ManyToMany aquí
	        curso1 = new Curso();
	        curso1.setDescripcion("Curso de Matemáticas");
	        estudiante1 = new Estudiante();
	        estudiante1.setNombre("Juan");

	        Set<Estudiante> estudiantes = new HashSet<>();
	        estudiantes.add(estudiante1);

	        curso1.setEstudiantes(estudiantes);
	        estudiante1.setCursos(Set.of(curso1));

	        /*
	         * . persist solo indica a JPA que estas entidades deberían ser guardadas
	         *  una vez que se ejecute la próxima transacción.
	         */
	        entityManager.persist(curso1);
	        entityManager.persist(estudiante1);
	        
	        /*
	         * Llamar a flush es útil porque te permite verificar 
	         * el estado de la base de datos inmediatamente después 
	         * de ejecutar ciertas operaciones, sin tener que esperar 
	         * a que la transacción se complete
	         */
	        entityManager.flush();
	    }

	    @Test
	    @Transactional
	    public void cuandoGuardaRelacion_thenEncuentraCursoYEstudiante() {
	        Curso cursoEncontrado = entityManager.find(Curso.class, curso1.getId());
	        Estudiante estudianteEncontrado = entityManager.find(Estudiante.class, estudiante1.getId());

	        assertThat(cursoEncontrado.getEstudiantes()).contains(estudiante1);
	        assertThat(estudianteEncontrado.getCursos()).contains(curso1);
	    }
}
