package vr.estudiantes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vr.estudiantes.modelo.Estudiante;
import vr.estudiantes.servicio.EstudianteServicio;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
//Para poder ejecutar nuestra aplicacion por consola implementaremos la interface CommandLineRunner
//Esto le indica a Spring que ejecutaremos toda nuestra aplicacion por consola
//Al implementar esta Interface tendremos que implementar el metodo que tiene la misma, es decir el metodo run();
public class EstudiantesApplication implements CommandLineRunner {

	//Para acceder desde esta capa de Presentacion a la capa de Servicio tenemos que inyectar la dependencia
	//mediante la notacion @Autowired
	@Autowired
	private EstudianteServicio estudianteServicio;

	//Configuraremos nuestro Logger para no usar uno tan generico como el System.out.println();
	//Esto debido a que ya estamos trabajando con un Framework como es Spring.
	//Con esto definimos el Logger que usaremos y a su vez le indicamos desde que clase le estara llegando
	//informacion, esto tambien nos sirve para activarlo o desactivarlo conforme necesitemos enviar informacion
	//a este mismo.
	private static final Logger logger = LoggerFactory.getLogger(EstudiantesApplication.class);

	//Imprimimos un salto de linea
	//Lo haremos de esta manera para que sea mas generico y funcione en todos los sistemas operativos
	String nl = System.lineSeparator();
	//Esto nos retorna el caracter correcto segun el sistema operativo con el que trabajemos

	//Nuestro metodo Main solo ejecuta la fabrica de Spring, no
	public static void main(String[] args) {
		//Mostramos algunos mensajes para poder comprobar el funcionamiento de nuestra aplicacion
		logger.info("Iniciando la Aplicacion...");

		//Levanta la fabrica de Spring
		SpringApplication.run(EstudiantesApplication.class, args);

		logger.info("Fin de la ejecucion...");
	}

	//El metodo run(); se ejecutara inmediatamente despues de que el metodo Main ejecute nuestra fabrica
	//de Spring, es en este metodo donde agregaremos nuestra logica de "negocio" (presentacion).
	//NO podemos elaborar esta logica en el metodo MAIN ya que tendriamos que utilizar o extraer informacion
	//de la fabrica de Spring de manera manual, ya que no queremos hacer esto trabajaremos a traves del
	//metodo run();
	@Override
	public void run(String... args) throws Exception {
		logger.info(nl + "Ejecutando metodo run de Spring..." + nl);

		var scanner = new Scanner(System.in);
		var salir = false;

		while (!salir) {
			//En esta ocasion no agregamos los bloques de codigo Try y Catch ya que el metodo run nos lanza
			//una excepcion por defecto
			mostraMenu();
			salir = opciones(scanner);
			logger.info(nl);
		}
	}

	public void mostraMenu() { //Metodo para mostrar el menu
		logger.info(nl + """
				*** Sistema de Estudiantes ***
				1. Listar Estudiantes
				2. Buscar Estudiante
				3. Agregar Estudiante
				4. Modificar Estudiante
				5. Eliminar Estudiante
				6. Salir
				Elige una opcion:""" + nl);
	}

	public boolean opciones(Scanner scanner) { //Metodo para definir las acciones segun la opcion
		var opcion = Integer.parseInt(scanner.nextLine());
		var salir = false;

		switch (opcion) {

			case 1 -> { //Listar estudiantes - READ
				logger.info(nl + "Listado de estudiantes: " + nl);

				//Definimos una variable de tipo List que contendra Estudiantes y su valor sera igual a la lista
				//directa de estudiantes de la base de datos.
				List<Estudiante> estudiantes = estudianteServicio.listarEstudiante();

				//Usamos una funcion lambda para mostrar a cada estudiante, solo que ahora tenemos que usar
				//de manera explicita el metodo toString(); para poder visualizar la informacion.
				estudiantes.forEach(estudiante -> logger.info(estudiante.toString() + nl));
			}

			case 2 -> { //Buscar estudiante por Id - READ
				logger.info("Ingrese el Id del estudiante que desea buscar: ");
				//Pedimos el Id del estudiante
				var id = Integer.parseInt(scanner.nextLine());
				//Buscamos al estudiante con el metodo de estudianteServicio
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(id);
				if(estudiante != null) {
					logger.info("Se encontro al estudiante: " + estudiante + nl);
				} else {
					logger.info("Estudiante con id " + id + " no encontrado" + nl);
				}
			}

			case 3 -> { //Agregar estudiante - CREATE
				logger.info("Agregar estudiante: " + nl);
				logger.info("Nombre: ");
				var nombre = scanner.nextLine();
				logger.info("Apellido: ");
				var apellido = scanner.nextLine();
				logger.info("Telefono: ");
				var telefono = scanner.nextLine();
				logger.info("Email: ");
				var email = scanner.nextLine();

				//Creamos nuestro objeto estudiante sin el Id y seteamos los valores
				//Se crea de esta manera ya que no tenemos el constructor que no pide Id, entonces
				//seteamos manualmente los valores con los metodos Set();
				var estudiante = new Estudiante();
				estudiante.setNombre(nombre);
				estudiante.setApellido(apellido);
				estudiante.setTelefono(telefono);
				estudiante.setEmail(email);

				//Guardamos el estudiante
				estudianteServicio.guardarEstudiante(estudiante);

				logger.info("Estudiante agregado: " + estudiante + nl);
			}
			case 4 -> { //Modificar estudiante - UPDATE
				logger.info("Modificar estudiante" + nl);
				logger.info("Id del estudiante: ");
				var idEstudiante = Integer.parseInt(scanner.nextLine());

				//Buscamos el estudiante por Id para confirmar si existe, en caso de existir pedira los datos
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(idEstudiante);
				if(estudiante != null) {
					logger.info("Nombre: ");
					var nombre = scanner.nextLine();
					logger.info("Apellido: ");
					var apellido = scanner.nextLine();
					logger.info("Telefono: ");
					var telefono = scanner.nextLine();
					logger.info("Email: ");
					var email = scanner.nextLine();

					//Seteamos los valores
					estudiante.setNombre(nombre);
					estudiante.setApellido(apellido);
					estudiante.setTelefono(telefono);
					estudiante.setEmail(email);

					//Modificamos el estudiante en la base de datos
					estudianteServicio.guardarEstudiante(estudiante);

					logger.info("Estudiante modificado: " + estudiante + nl);
				} else {
					logger.info("Estudiante no encontrado con el id: " + idEstudiante + nl);
				}
			}
			case 5 -> { //Eliminar estudiante - DELETE
				logger.info("Eliminar estudiante: " + nl);
				logger.info("Proporciona el id del estudiante a eliminar: ");
				var id = Integer.parseInt(scanner.nextLine());

				//Buscamos si el estudiante existe para saber si podemos eliminarlo
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorId(id);
				if(estudiante != null) {
					estudianteServicio.eliminarEstudiante(estudiante);
					logger.info("Estudiante eliminado " + estudiante + nl);
				} else {
					logger.info("Estudiante con el id " + id + " no encontrado");
				}
			}
			case 6 -> {
				logger.info("Hasta pronto..." + nl + nl);
				salir = true;
			}
			default -> {
				logger.info("Opcion no valida: " + opcion + nl);
			}
		}
		return salir;
	}
}
