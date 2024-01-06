package vr.estudiantes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import vr.estudiantes.modelo.Estudiante;

//Creamos una Interface para extender sus funcionalidades de la interface JpaRepository, tambien tenemos que indicar
//la clase de Entidad que se va a utilizar para generar la comunicacion con la base de datos (Estudiante)
//Tambien tenemos que indicar como segundo parametro el tipo de dato de la llave primaria de esta entidad,
//en este caso es de tipo Integer.

//Extendemos funcionalidades de la Interface JpaRepository para tener metodos como Deletes, GetById, FindAll
//entre otros, estos metodos los tenemos de manera automatica solo por crear esta interface y extenderla
//de JpaRepository.

//Anteriormente se tenia que especificar con la etiqueta de Repository para indicar a Spring que esta era una
//clase de tipo Repository pero con esta notacion ya no es necesario y se detecta de manera automatica como
//una clase de tipo Repository
// @Repository  <---  Anteriormente
public interface EstudianteRepositorio extends JpaRepository<Estudiante, Integer> {
}
