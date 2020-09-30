package de.clumsystuff.spring_boot_rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
