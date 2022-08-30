package com.example.rabbitmq.publisher;

import com.example.rabbitmq.custom.MessageCustom;
import com.example.rabbitmq.dto.OderDTO;
import com.example.rabbitmq.dto.OderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/rbmq")
public class OderPublisherController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private RabbitTemplate template;

    @PostMapping("/publisher")
    public ResponseEntity publisher(
            @RequestBody OderDTO oderDTO,
            @RequestParam("name") String name
    ){
        oderDTO.setId(UUID.randomUUID().toString());
        OderStatus oderStatus = new OderStatus(oderDTO,"status","Oder successfuly in "+name);
        template.convertAndSend(MessageCustom.EXCHANGE_NAME,MessageCustom.ROUTING_KEY,oderStatus);
        return ResponseEntity.ok().body(oderStatus);
    }



}
