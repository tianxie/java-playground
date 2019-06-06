package me.txie.springamqpretry;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ScheduledSender {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;
    private final AtomicInteger counter = new AtomicInteger();

    public ScheduledSender(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedRate = 10000)
    public void sendMessage() throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(Application.queueName, counter.incrementAndGet());
    }

}
