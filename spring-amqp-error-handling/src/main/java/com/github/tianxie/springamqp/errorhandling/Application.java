package com.github.tianxie.springamqp.errorhandling;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        sendMessage();
    }

    public static final String QUEUE_MESSAGES = "baeldung-messages-queue";
    public static final String EXCHANGE_MESSAGES = "baeldung-messages-exchange";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private int messageNumber = 0;

    @Bean
    Queue messagesQueue() {
        return QueueBuilder.durable(QUEUE_MESSAGES)
                .build();
    }

    @Bean
    DirectExchange messagesExchange() {
        return new DirectExchange(EXCHANGE_MESSAGES);
    }

    @Bean
    Binding bindingMessages() {
        return BindingBuilder.bind(messagesQueue()).to(messagesExchange()).with(QUEUE_MESSAGES);
    }

    public void sendMessage() {
        rabbitTemplate.convertAndSend(EXCHANGE_MESSAGES,
                QUEUE_MESSAGES, "Some message id:" + messageNumber++);
    }

    @RabbitListener(queues = QUEUE_MESSAGES)
    public void receiveMessage(Message message) throws BusinessException {
        throw new BusinessException();
    }
}

