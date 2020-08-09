package com.poc.twitter.providers;

import java.util.UUID;

import org.apache.kafka.clients.producer.ProducerConfig;

import com.poc.twitter.Tweet;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.context.annotation.Property;

@KafkaClient(
        id="tweet-client",
        acks = KafkaClient.Acknowledge.ALL,
        properties = @Property(name = ProducerConfig.RETRIES_CONFIG, value = "0")
)
public interface KafkaTweetClient{

    @KafkaClient(batch=true)
    @Topic("tweets")
    void sendTweet(@KafkaKey UUID id, Tweet tweet);
}
