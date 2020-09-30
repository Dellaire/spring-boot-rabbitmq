package de.clumsystuff.spring_boot_rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
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
	Binding binding(Queue queue, TopicExchange exchange) {

		return BindingBuilder.bind(queue).to(exchange).with("dataKey");
	}
}