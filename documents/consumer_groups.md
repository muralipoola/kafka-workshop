####Console Consumer with Group
```shell script
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my-first-topic --group group1
```
####Alter topic to have 2 partitions
```shell script
bin/kafka-topics.sh --bootstrap-server localhost:9092 --alter --topic my-first-topic \
      --partitions 2
```

####List all consumers available
```shell script
bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list
```

####Verify Lag for all consumer groups
```shell script
bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --all-groups
```

####Verify Lag for specific consumer group
```shell script
bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group group1
```

