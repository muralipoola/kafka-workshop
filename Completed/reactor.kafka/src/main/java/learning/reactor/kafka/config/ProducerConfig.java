package learning.reactor.kafka.config;

public class ProducerConfig {

  private String keySerializer;
  private String valueSerializer;
  private String acksConfig;

  public String getKeySerializer() {
    return keySerializer;
  }

  public void setKeySerializer(String keySerializer) {
    this.keySerializer = keySerializer;
  }

  public String getValueSerializer() {
    return valueSerializer;
  }

  public void setValueSerializer(String valueSerializer) {
    this.valueSerializer = valueSerializer;
  }

  public String getAcksConfig() {
    return acksConfig;
  }

  public void setAcksConfig(String acksConfig) {
    this.acksConfig = acksConfig;
  }
}
