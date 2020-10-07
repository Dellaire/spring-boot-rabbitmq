package de.clumsystuff.spring_boot_rabbitmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfiguration {

	@Bean
	Queue queue() {
		return new Queue("dataQueue", false);
	}

	@Bean
	TopicExchange exchange() {
		
		return new TopicExchange("dataExchange");
	}
	
	@Bean
	CustomExchange delayedExchange() {
		
		Map<String, Object> args = new HashMap<String, Object>();
	    args.put("x-delayed-type", "direct");
	    
	    return new CustomExchange("delayedDataExchange", "x-delayed-message", true, false, args);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {

		return BindingBuilder.bind(queue).to(exchange).with("dataKey");
	}
	
	@Bean
	Binding delayedBinding(Queue queue, CustomExchange delayedExchange) {

		return BindingBuilder.bind(queue).to(delayedExchange).with("dataKey").noargs();
	}
}
