package com.gk.maindriver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.gk.listener.ProductMessageListener;

/**
 * 
 */

/**
 * @author gauravkhandave
 *
 */
@SpringBootApplication
@EnableMongoRepositories("com.gk.repository")
@ComponentScan("com.gk.listener")
@ComponentScan("com.gk.converters")
@ComponentScan("com.gk.entities")
@ComponentScan("com.gk.model")
@ComponentScan("com.gk.controllers")
@ComponentScan("com.gk.servicesImpl")
@EnableAutoConfiguration
@EnableWebMvc
public class RabbitMQSpringBootApplication {
	
	public final static String GSK_MESSAGE_QUEUE = "gsk-message-queue";
	
	@Bean
	Queue queue() {
		return new Queue(GSK_MESSAGE_QUEUE, false);
	}
	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange("spring-boot-exchange");
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(GSK_MESSAGE_QUEUE);
	}

	@Bean
	MessageListenerAdapter listenerAdapter(ProductMessageListener receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
	MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(GSK_MESSAGE_QUEUE);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitMQSpringBootApplication.class, args);
	}

}
