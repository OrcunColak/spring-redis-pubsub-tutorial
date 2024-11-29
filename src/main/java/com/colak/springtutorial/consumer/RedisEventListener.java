package com.colak.springtutorial.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

// This listener does not derive from MessageListener. But it is wrapped with MessageListenerAdapter
@Service
@Slf4j
public class RedisEventListener {

    public void listen(Object message) {
        log.info("New event received: {}", message);
    }
}
