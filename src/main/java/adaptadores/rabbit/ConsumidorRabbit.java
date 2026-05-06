package adaptadores.rabbit;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;

import puertos.PuertoEntrada;

public class ConsumidorRabbit {
	
	private PuertoEntrada puertoEntradaEventos;

	public ConsumidorRabbit(PuertoEntrada puertoEntradaEventos) {
		this.puertoEntradaEventos = puertoEntradaEventos;
	}
	
	 @RabbitListener(queues = RabbitMQConfiguration.QUEUE)
	    public void recibirEvento(Map<String, String> mensaje, @Header("amqp_receivedRoutingKey") String routingKey) {
	      

	        try {
	            switch (routingKey) {
	                case "bus.usuarios.usuario-modificado":
	                    puertoEntradaEventos.manejarUsuarioEliminado(mensaje.get("nombrePropietario"));
	                    break;
	                default:
	                    System.out.println("Evento no reconocido.");
	            }
	        } catch (Exception e) {
	        	System.out.println("Error: " + e.getMessage());
	        }
	    }

}
