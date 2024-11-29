package com.colak.springtutorial.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

// This listener derives from MessageListener
@Service
@Slf4j
public class RedisEventListener2 implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String string = new String(message.getBody());
        log.info("\"New event received: {} ", string);
    }
}
