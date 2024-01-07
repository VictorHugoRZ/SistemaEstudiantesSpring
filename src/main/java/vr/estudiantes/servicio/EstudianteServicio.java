package vr.estudiantes.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vr.estudiantes.modelo.Estudiante;
import vr.estudiantes.repositorio.EstudianteRepositorio;

import java.util.List;
//Podemos tomar a Spring como una fabrica de objetos, ya que podemos definir Entidades (Entity),
// Repositorios (Repository) y Servicios (Service).

//Definimos esta clase para brindar un servicio
@Service //Tenemos que agregar esta notacion para que Spring pueda reconocer esta clase como un componente y
         //a su vez este componente pueda recibir dependencias con la etiqueta Autowired de la fabrica de Spring.
         //De lo contrario, si no agregamos la notacion de @Service no podremos inyectar las dependencias
         //que necesitamos.
public class EstudianteServicio implements IEstudianteServicio{

    @Autowired //Indicando esto podemos inyectar dependencias
    private EstudianteRepositorio estudianteRepositorio;
    //Definiendo esta variable Spring inyectara esta dependencia a la clase y podremos usar sus metodos

    @Override
    public List<Estudiante> listarEstudiante() {
        //Definimos una variable de tipo List que contendra Estudiantes, para definir su valor usaremos
        //la variable de repositorio y a su vez el metodo que ya tiene esta para listar directamente desde
        //la base de datos
        List<Estudiante> estudiantes = estudianteRepositorio.findAll();
        return estudiantes;
    }

    @Override
    public Estudiante buscarEstudiantePorId(Integer idEstudiante) {
        //El metodo findById nos retorna un objeto de tipo Optional, nosotros tenemos que especificar
        //lo que queremos que suceda con el valor en caso de ser nulo o en caso de obtener alguna excepcion
        //En caso de ser nulo usaremos el metodo orElse(); para setear el valor a nulo (null).
        Estudiante estudiante = estudianteRepositorio.findById(idEstudiante).orElse(null);
        return estudiante;
    }

    @Override
    public void guardarEstudiante(Estudiante estudiante) {
        //Este es un metodo muy particular ya que podemos realizar distintas operaciones con el.

        //Si el objeto de tipo Estudiante que le pasamos como argumento NO tiene la propiedad IdEstudiante,
        //entonces el metodo detactara esto y creara un nuevo registro en nuestra base de datos.

        //Si el objeto tiene todas sus propiedades, incluida el IdEstudiante entonces el metodo lo detactara
        //y actualizara el registro correspondiente en la base de datos.

        //El metodo save(); se utiliza para Insercion (Insert) o Actualizacion (Update).
        estudianteRepositorio.save(estudiante);
    }

    @Override
    public void eliminarEstudiante(Estudiante estudiante) {
        //El metodo delete buscara y relacionara el Id del Estudiante para eliminar ese registro de la base
        //de datos, tambien existe el metodo deleteById(); que realiza esta tarea de manera mas concreta y solo
        //con el Id del estudiante, a diferencia del metodo Delete que puede relacionar mas campos para
        //poder eliminar registros.
        estudianteRepositorio.delete(estudiante);
    }
}
