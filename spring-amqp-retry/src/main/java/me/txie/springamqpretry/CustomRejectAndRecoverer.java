package me.txie.springamqpretry;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;

public class CustomRejectAndRecoverer implements MessageRecoverer {

    @Override
    public void recover(Message message, Throwable cause) {
        if (ExceptionUtils.getRootCause(cause) instanceof BusinessObjectNotFoundRuntimeException) {
//            throw new ListenerExecutionFailedException("Retry Policy Exhausted",
//                    new AmqpRejectAndDontRequeueException(cause), message);
            System.out.println("Retry Policy Exhausted");
        }
    }
}
