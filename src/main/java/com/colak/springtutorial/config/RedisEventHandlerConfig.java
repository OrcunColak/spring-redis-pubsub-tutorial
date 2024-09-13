package com.colak.springtutorial.config;

import com.colak.springtutorial.consumer.RedisEventListener;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
@RequiredArgsConstructor
public class RedisEventHandlerConfig {

    // My listener code
    private final RedisEventListener redisEventListener;

    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter(redisEventListener, "listen");
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(ChannelTopic pubSubChannelTopic,
                                                                       RedisConnectionFactory redisConnectionFactory) {

        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        redisMessageListenerContainer.addMessageListener(messageListenerAdapter(), pubSubChannelTopic);
        return redisMessageListenerContainer;
    }
}
