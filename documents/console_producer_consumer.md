####Console Producer
```shell script
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic my-first-topic
```

####Console Consumer
```shell script
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my-first-topic --from-beginning
```

####Console Consumer printing key information
```shell script
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my-first-topic --from-beginning --property print.key=true
```