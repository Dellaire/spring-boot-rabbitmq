package de.clumsystuff.spring_boot_rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Controller;

@Controller
public class AmqpDataListener {

	private final static Logger logger = LoggerFactory.getLogger(AmqpDataListener.class);
	
	@RabbitListener(queues = "dataQueue")
	public void receiveData(String data) {
		
		logger.info("Received data from RabbitMQ: {}", data);
	}
}
