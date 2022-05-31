package net.codejava;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListner {

	@RabbitListener(queues=MQConfig.QUEUE)
	public void Listner(CustomMessage message)
	{
		System.out.println(message);
	}
}
