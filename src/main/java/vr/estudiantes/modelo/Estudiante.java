package vr.estudiantes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity //Definimos esta clase como una entidad para persistir en la base de datos

//Boilerplate - codigo repetitivo que podemos evitar con Lombok
//Con Lombok podemos agregar un par de lineas de codigo o etiquetas para simplificar
//la generacion de metodos como Get, Set, ToString y los Constructores de nuestra clase.
@Data //Genera los metodos Get y Set
@NoArgsConstructor //Genera el constructor vacio
@AllArgsConstructor //Genera el constructor con todos los argumentos
@ToString //Sobre escribe el metodo ToString

public class Estudiante {
    //Estas etiquetas afectan al primer valor (idEstudiante)
    @Id //Se define como un Identificador
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Se define como un valor auto incrementable
    private Integer idEstudiante;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
}

//Gracias a esto podemos evitar escribir demasiadas lineas de codigo con metodos y constructores.
