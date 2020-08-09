package com.poc.twitter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;



import scala.Tuple2;

public class Application {

    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf()
                .setAppName("TweetsAnalyzer")
                .setMaster("spark://spark-master:7077");

        JavaStreamingContext streamingContext = new JavaStreamingContext(
                sparkConf, Durations.seconds(1));

        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", "kafka:9093");
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", "use_a_separate_group_id_for_each_stream");
        kafkaParams.put("auto.offset.reset", "latest");

        List<String> topics = Arrays.asList("tweets");

        JavaInputDStream<ConsumerRecord<String, String>> tweets = KafkaUtils.createDirectStream(streamingContext,
                LocationStrategies.PreferConsistent(),
                ConsumerStrategies.<String, String> Subscribe(topics, kafkaParams));

        final JavaPairDStream<String, String> result = tweets.mapToPair(tweet -> {
            System.out.println(String.format("id %s and tweet %s", tweet.key(), tweet.value()));
            return new Tuple2<>(tweet.key(), tweet.value());
        });


        try {
            result.print();
            streamingContext.start();
            streamingContext.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
