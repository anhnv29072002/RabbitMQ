package com.example.rabbitmq.consumer;

import com.example.rabbitmq.config.MessageConfig;
import com.example.rabbitmq.custom.MessageCustom;
import com.example.rabbitmq.dto.OderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    @RabbitListener(queues = MessageCustom.QUEUE_NAME)
    public void consumerMessageFromQueue(OderStatus oderStatus){
        System.out.println("Response:  "+oderStatus);
    }

}
