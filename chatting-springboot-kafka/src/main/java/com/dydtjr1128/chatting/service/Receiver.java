package com.dydtjr1128.chatting.service;

import com.dydtjr1128.chatting.model.ChattingMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private SimpMessagingTemplate template;

    //@KafkaListener(id="main-listener", topics = "${topic.boot}")
    @KafkaListener(id="main-listener", topics = "kafka-chatting")
    public void receive(ChattingMessage message) throws Exception {
        //String[] message = consumerRecord.value().toString().split("\\\\u0001");
        //String message = consumerRecord.value().toString();
        LOGGER.info("message='{}'", message);
        HashMap<String, String> msg = new HashMap<>();
        msg.put("timestamp",Long.toString(message.getTimeStamp()));
        msg.put("message",message.getMessage());
        msg.put("author",message.getUser());


        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(msg);

        System.out.println("@@@@@@@@@@@@@" + json);
        this.template.convertAndSend("/topic/public", json);
    }
}