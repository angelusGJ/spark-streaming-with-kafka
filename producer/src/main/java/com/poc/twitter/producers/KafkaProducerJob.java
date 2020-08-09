package com.poc.twitter.producers;

import java.util.UUID;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javafaker.Faker;
import com.poc.twitter.Tweet;
import com.poc.twitter.providers.KafkaTweetClient;

import io.micronaut.scheduling.annotation.Scheduled;

@Singleton
public class KafkaProducerJob{
    private Logger LOG = LoggerFactory.getLogger(KafkaProducerJob.class);
    private KafkaTweetClient client;
    private Faker faker = new Faker();


    public KafkaProducerJob(final KafkaTweetClient client){
        this.client = client;
    }

    @Scheduled(fixedDelay = "10s")
    void produceFakeTweets() {
        final Tweet tweet = new Tweet(UUID.randomUUID(), faker.address().cityName(), faker.shakespeare().hamletQuote());

        LOG.info("Sending tween with id {} for location {} and payload {}", tweet.getId(), tweet.getLocation(), tweet.getPayload());
        client.sendTweet(tweet.getId(), tweet);
    }
}
