package me.txie.springamqpretry;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @RabbitListener(queues = Application.queueName)
    public void receiveMessage(int message) {
        System.out.println("Received <" + message + ">");
        if (message % 9 == 0) {
            throw new BusinessObjectNotFoundRuntimeException();
        }
    }
}
