package learning.reactor.kafka.json.config;

public class KafkaConfig {

  private String bootstrapServers;
  private ProducerConfig producerConfig;
  private ConsumerConfig consumerConfig;
  private String topicName;

  public String getBootstrapServers() {
    return bootstrapServers;
  }

  public void setBootstrapServers(String bootstrapServers) {
    this.bootstrapServers = bootstrapServers;
  }

  public ProducerConfig getProducerConfig() {
    return producerConfig;
  }

  public void setProducerConfig(ProducerConfig producerConfig) {
    this.producerConfig = producerConfig;
  }

  public ConsumerConfig getConsumerConfig() {
    return consumerConfig;
  }

  public void setConsumerConfig(ConsumerConfig consumerConfig) {
    this.consumerConfig = consumerConfig;
  }

  public String getTopicName() { return topicName;}

  public void setTopicName(String topicName) { this.topicName = topicName; }

}
