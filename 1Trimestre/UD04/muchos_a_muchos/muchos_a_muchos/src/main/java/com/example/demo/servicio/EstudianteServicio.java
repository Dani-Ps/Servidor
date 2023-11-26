package com.example.demo.servicio;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidad.Curso;
import com.example.demo.entidad.Estudiante;
import com.example.demo.repositorio.CursoRepositorio;
import com.example.demo.repositorio.EstudianteRepositorio;

import jakarta.transaction.Transactional;



@Service
public class EstudianteServicio {

    @Autowired
    private EstudianteRepositorio estudianteRepositorio;
    
    @Autowired
    private CursoRepositorio cursoRepositorio;


    public Estudiante guardarEstudiante(Estudiante estudiante) {
        // Aquí podrías añadir lógica de validación o negocio si es necesario
        return estudianteRepositorio.save(estudiante);
    }


	public List<Estudiante> obtenerTodos() {
		return estudianteRepositorio.findAll();
	}


	public Optional<Estudiante> obtenerPorId(Long id) {
	
		 return estudianteRepositorio.findById(id);
			      //  .orElseThrow(() -> new EstudianteNoEncontradoException("Estudiante no encontrado con el ID: " + id));
	}
	
	public List<Curso> obtenerTodosLosCursos(){
		return cursoRepositorio.findAll();
	}
	// La etiqueta @Transactional facilita el manejo correcto y eficiente de las transacciones en la base de datos, ayudando a asegurar la integridad y consistencia de los datos.
	/**
	 *
	 * 
	 * @param estudianteId
	 * @param cursoId
	 */
	@Transactional
	public void agregarCursoAEstudiante(Long estudianteId, Long cursoId) {
		Estudiante estudiante = estudianteRepositorio.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Curso curso = cursoRepositorio.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        
        
        estudiante.agregarCursos(curso);
        curso.agregarEstudiante(estudiante);
        
       
        estudianteRepositorio.save(estudiante);
        cursoRepositorio.save(curso); 

	}


	public Curso buscarCursoPorId(Long id) {
		return cursoRepositorio.findById(id).get();
	}
	
	public Curso guardarCurso(Curso curso) {
		return cursoRepositorio.save(curso);
	}


	public Set<Estudiante> obtenerEstudiantesPorCurso(Long cursoId) {
		Curso curso = buscarCursoPorId(cursoId);
		return curso.getEstudiantes();
	}

}
