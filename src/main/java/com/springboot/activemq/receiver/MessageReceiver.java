package com.springboot.activemq.receiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.springboot.activemq.models.Message;

@Component
public class MessageReceiver {
	private int count = 1;

	@JmsListener(destination = "javainuse")
	public void receiveMessage(Message message) {
		System.out.println("<" + count + "> Received <" + message + ">");
		count++;
	}
}
