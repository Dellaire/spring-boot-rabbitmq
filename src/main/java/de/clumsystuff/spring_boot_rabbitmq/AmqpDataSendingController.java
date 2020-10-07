package de.clumsystuff.spring_boot_rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class AmqpDataSendingController {

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
	public void sendData(@RequestBody String data) {
		
		this.amqpTemplate.convertAndSend("dataExchange", "dataKey", data);
	}
	
	@PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE, params = "delay")
	public void sendDataDelayed(@RequestBody String data, @RequestParam Long delay) {
		
		Message message = MessageBuilder.withBody(data.getBytes()).setHeader("x-delay", delay).build();
		
		this.amqpTemplate.convertAndSend("delayedDataExchange", "dataKey", message);
	}
}
