package com.colak.springtutorial.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisPublishService {

    private final RedisTemplate<String, Object> redisPubSubTemplate;

    // Redis Pub/Sub operates through channels that can have multiple or no subscribers at any time.
    // Publishers broadcast messages to these channels,
    // ChannelTopic is my bean
    private final ChannelTopic pubSubChannelTopic;

    public void publishEvent(Object object) {
        String topicName = pubSubChannelTopic.getTopic();
        log.info("Publishing event: {} to channel: {}", object, topicName);

        Long id = redisPubSubTemplate.convertAndSend(topicName, object);
        if (id != null) {
            log.info("event published to channel: {}", topicName);
        }
    }
}
