package com.rabbit.mq.direct.producer;

import com.rabbit.mq.RabbitMqProducerApplication;
import com.rabbit.mq.topic.producer.GoodsProducer;
import com.rabbit.mq.topic.producer.OrderProducer;
import com.rabbit.mq.topic.producer.UserProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitMqProducerApplication.class)
public class RabbitMqProducerApplicationTopicTests {
	
	@Autowired
	private UserProducer userProducer;
	
	@Autowired
	private GoodsProducer goodsProducer;
	
	@Autowired
	private OrderProducer orderProducer;
	
	/**
	 * send message using topic exchanger
	 */
	
	@Test
	public void produceTopicMsg() {
		while (true) {
			try {
				userProducer.sendMsg();
				Thread.sleep(5000);
				orderProducer.sendMsg();
				Thread.sleep(5000);
				goodsProducer.sendMsg();
				Thread.sleep(5000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
