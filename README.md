# spark-streaming-with-kafka

This project is a PoC to test Spark Streaming with Kafka. To Run the PoC you should must to install:

* docker 
* docker-compose

The PoC simulates a producer generating POJOs that are sent to a kafka topic. POJOs simulate tweets that have been retrieved using the Twitter API (this feature has been mocked).

Tweets are analyzed by a spark application using Spark Streaming. This application reads the messages left in the kafka topic and processes them. In this example it only traces the received message.

The comunication will be:

Producer -> Kafka -> Spark -> Spark Tweet Analyzers Application.

The Poc can be running to execute the command:

```
docker-compose up
```

This run:
* Zookeper Server: mapping the port 2181 in Docker Host.
* Kafka Server: mapping the port 9092 in Docker Host.
* Spark Master Node: mapping the port 8080 and 7077 in Docker Host.
* Spark Worker Node: mapping the port 8081 in Docker Host.
* Producer Application
* Consumer Spark Application

The Spark Master and The Spark Master UI listen in http://localhost:8080 and Spark Worker Node in http://localhost:8081. 

> :warning: **There is a problem when you want to see the logs produced by Consumer Spark Application. The generated URL by the server uses the docker container ip, you should change to localhost to get the logs.**
