package com.colak.springtutorial.config;

import com.colak.springtutorial.consumer.RedisEventListener;
import com.colak.springtutorial.consumer.RedisEventListener2;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
@RequiredArgsConstructor
public class RedisEventHandlerConfig {

    @Value("${redis.pubsub.topic}")
    String topic;

    // My listener code
    private final RedisEventListener redisEventListener;

    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter(redisEventListener, "listen");
    }

    // Redis Pub/Sub operates through channels that can have multiple or no subscribers at any time.
    // Subscribers connected to those channels receive them in real-time.
    // ChannelTopic is my bean
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(ChannelTopic pubSubChannelTopic,
                                                                       RedisConnectionFactory redisConnectionFactory,
                                                                       RedisEventListener2 redisEventListener2) {

        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);

        // Another way
        redisMessageListenerContainer.addMessageListener(redisEventListener2, new PatternTopic(topic));

        redisMessageListenerContainer.addMessageListener(messageListenerAdapter(), pubSubChannelTopic);
        return redisMessageListenerContainer;
    }
}
