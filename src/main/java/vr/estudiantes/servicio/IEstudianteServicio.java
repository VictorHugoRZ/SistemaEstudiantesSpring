package vr.estudiantes.servicio;

import vr.estudiantes.modelo.Estudiante;

import java.util.List;

public interface IEstudianteServicio {
    //Definimos los metodos de la Interface que se van a sobre escribir cuando la clase EstudianteServicio
    //los implemente.

    //Metodo que retorna una lista de tipo Estudiante
    public List<Estudiante> listarEstudiante();

    //Metodo que retorna un objeto de tipo Estudiante despues de buscarlo en la base de datos
    public Estudiante buscarEstudiantePorId(Integer idEstudiante);

    //Metodo que sirve para modificar, guardar o insertar a un estudiante en la base de datos
    //De manera interna se asocian y ejecutan otros metodos gracias a Jpa
    public void guardarEstudiante(Estudiante estudiante);

    //Metodo para eliminar un estudiante de la base de datos
    public void eliminarEstudiante(Estudiante estudiante);
}
