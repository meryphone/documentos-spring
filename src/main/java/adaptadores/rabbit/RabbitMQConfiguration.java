package adaptadores.rabbit;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.GsonMessageConverter;


@Configuration
public class RabbitMQConfiguration {
	
	public static final String EXCHANGE = "bus";
	
	public static final String QUEUE = "documentos";
	
	// Referencias al exchange y cola (creados por rabbitmq-setup)
    @Bean
    public TopicExchange busExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue documentosQueue() {
        return new Queue(QUEUE, true);
    }

    // --- Suscripciones (bindings) ---

    @Bean
    public Binding compraventasBindingUsuariosEliminado(Queue documentosQueue, TopicExchange busExchange) {
        return BindingBuilder.bind(documentosQueue).to(busExchange).with("bus.usuarios.usuario-eliminado");
    }



    // --- Mensajeria ---

    @Bean
    public GsonMessageConverter gsonMessageConverter() {
        return new GsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter gson) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(gson);
        return template;
    }
	
	

}
