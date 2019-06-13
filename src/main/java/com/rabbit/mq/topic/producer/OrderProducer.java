package com.rabbit.mq.topic.producer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer {
	
	@Autowired
	private AmqpTemplate rabbitTamplate;
	
	@Value("${mq.config.topic.exchange}")
	private String exchanger;

	@Value("${mq.config.quene.goods.debug.routing.key}")
	private String goodsDebugRoutingKey;

	@Value("${mq.config.quene.goods.warn.routing.key}")
	private String goodsWarnRoutingKey;

	@Value("${mq.config.quene.goods.info.routing.key}")
	private String goodsInfoRoutingKey;

	@Value("${mq.config.quene.goods.error.routing.key}")
	private String goodsErrorRoutingKey;
	
	public void sendMsg() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.rabbitTamplate.convertAndSend(exchanger, goodsDebugRoutingKey, "order.log.debug......" + df.format(new Date()));
		this.rabbitTamplate.convertAndSend(exchanger, goodsInfoRoutingKey, "order.log.info......" + df.format(new Date()));
		this.rabbitTamplate.convertAndSend(exchanger, goodsWarnRoutingKey, "order.log.warn......" + df.format(new Date()));
		this.rabbitTamplate.convertAndSend(exchanger, goodsErrorRoutingKey, "order.log.error......" + df.format(new Date()));
	}
	
}
