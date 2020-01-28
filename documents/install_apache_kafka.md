####Download Apache Kafka
```shell script
curl -O http://apachemirror.wuchna.com/kafka/2.4.0/kafka_2.12-2.4.0.tgz && tar zxvf kafka_2.12-2.4.0.tgz && 
cd kafka_2.12-2.4.0
```

####Start Zookeeper
```shell script
bin/zookeeper-server-start.sh config/zookeeper.properties
```

####Start Kafka Broker
```shell script
bin/kafka-server-start.sh  config/server.properties
```