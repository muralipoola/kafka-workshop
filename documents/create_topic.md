####Create Kafka Topic Examples
```shell script
$kafka_home/bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic learning.apache.kafka --partitions 1 --replication-factor 1 
$kafka_home/bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic learning.spring.kafka.json --partitions 3 --replication-factor 1
$kafka_home/bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic employee.topic --partitions 3 --replication-factor 1
$kafka_home/bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic course.topic  --partitions 3 --replication-factor 1
```

####Alter Kafka Topic
```shell script
bin/kafka-topics.sh --bootstrap-server localhost:9092 --alter --topic my-first-topic \
      --partitions 2
```