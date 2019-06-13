package com.rabbit.mq.direct.producer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ErrorProducer {
	
	@Autowired
	private AmqpTemplate rabbitTamplate;
	
	@Value("${mq.config.direct.exchange}")
	private String exchanger;
	
	@Value("${mq.config.quene.error.routing.key}")
	private String errorLogRoutingKey;
	
	public void produceMsg() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String msg = "Hello Error:" + df.format(new Date());
		this.rabbitTamplate.convertAndSend(exchanger, errorLogRoutingKey, msg);
	}
	
}
