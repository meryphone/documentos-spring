package documentos_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Queremos desarrollar un servicio para gestionar documentos. Un documento se define con
las siguientes propiedades: identificador, propietario, colaboradores y contenido (texto). El
propietario y colaboradores se representan como cadenas de texto.
La funcionalidad del servicio es la siguiente: alta, recuperación y eliminación de un
documento, añadir y eliminar un colaborador y obtener los documentos de un propietario.
El ejercicio consiste en implementar un microservicio de acuerdo con las
recomendaciones estudiadas en la asignatura (API REST con buenas prácticas de diseño,
patrones repositorio y servicio, control de precondiciones). Los requisitos técnicos son:
• Persistencia: MongoDB
• Plataforma: Spring Boot
Sobre el controlador REST, solo deben implementarse las operaciones adaptadoras del
alta, borrado, recuperación y añadir un colaborador.
Finalmente, se incluirá un consumidor de eventos que procese el evento "usuario
eliminado" emitido por el microservicio “usuarios”. El tratamiento del evento consiste en
borrar todos los documentos del usuario eliminado.
Resumen configuración:
• Nombre del proyecto: documentos-spring
• Proyecto Maven con Spring Boot 2.6.1
• Características: starter-web, starter-data-mongodb y starter-amqp
 */


@SpringBootApplication
public class DocumentosSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentosSpringApplication.class, args);
	}

}
