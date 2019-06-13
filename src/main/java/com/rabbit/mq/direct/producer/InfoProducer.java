package com.rabbit.mq.direct.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class InfoProducer {
	
	@Autowired
	private AmqpTemplate rabbitTamplate;
	
	@Value("${mq.config.direct.exchange}")
	private String exchanger;
	
	@Value("${mq.config.quene.info.routing.key}")
	private String infoLogRoutingKey;
	
	public void produceMsg() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm	:ss");
		String msg = "Hello Info:" + df.format(new Date());
		this.rabbitTamplate.convertAndSend(exchanger, infoLogRoutingKey, msg);
	}
	
}
