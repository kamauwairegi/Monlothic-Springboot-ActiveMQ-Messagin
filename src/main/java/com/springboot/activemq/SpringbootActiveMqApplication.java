package com.springboot.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.springboot.activemq.models.Message;

@SpringBootApplication
public class SpringbootActiveMqApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringbootActiveMqApplication.class, args);

		JmsTemplate jms = ctx.getBean(JmsTemplate.class);

		// this can be after service execution in a controller or service itself in a
		// real application
		Message message = new Message();
		message.setId(1L);
		message.setMessage("ActiveMQ test message");
		System.out.println("Sending a message : " + message);

		jms.convertAndSend("javainuse", message);
	}

	// Serialize message content to json using TextMessage
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
}
