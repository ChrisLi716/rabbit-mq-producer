package com.rabbit.mq.direct.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rabbit.mq.RabbitMqProducerApplication;
import com.rabbit.mq.topic.producer.GoodsProducer;
import com.rabbit.mq.topic.producer.OrderProducer;
import com.rabbit.mq.topic.producer.UserProducer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitMqProducerApplication.class)
public class RabbitMqProducerApplicationDirectTests {
	
	@Autowired
	private ErrorProducer errorProducer;
	
	@Autowired
	private InfoProducer infoProducer;
	
	/**
	 * send message using direct exchanger
	 */
	@Test
	public void produceDirectMsg() {
		while (true) {
			try {
				errorProducer.produceMsg();
				Thread.sleep(5000);
				infoProducer.produceMsg();
				Thread.sleep(5000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
