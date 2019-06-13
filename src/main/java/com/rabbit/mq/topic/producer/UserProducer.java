package com.rabbit.mq.topic.producer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {
	
	@Autowired
	private AmqpTemplate rabbitTamplate;
	
	@Value("${mq.config.topic.exchange}")
	private String exchanger;
	
	@Value("${mq.config.quene.user.debug.routing.key}")
	private String userDebugRoutingKey;
	
	@Value("${mq.config.quene.user.warn.routing.key}")
	private String userWarnRoutingKey;
	
	@Value("${mq.config.quene.user.info.routing.key}")
	private String userInfoRoutingKey;
	
	@Value("${mq.config.quene.user.error.routing.key}")
	private String userErrorRoutingKey;
	
	public void sendMsg() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.rabbitTamplate
			.convertAndSend(exchanger, userDebugRoutingKey, "user.log.debug......" + df.format(new Date()));
		this.rabbitTamplate
			.convertAndSend(exchanger, userInfoRoutingKey, "user.log.info......" + df.format(new Date()));
		this.rabbitTamplate
			.convertAndSend(exchanger, userWarnRoutingKey, "user.log.warn......" + df.format(new Date()));
		this.rabbitTamplate
			.convertAndSend(exchanger, userErrorRoutingKey, "user.log.error......" + df.format(new Date()));
	}
	
}
