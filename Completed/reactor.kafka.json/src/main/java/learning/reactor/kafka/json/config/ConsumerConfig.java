package learning.reactor.kafka.json.config;

public class ConsumerConfig {

  private String keyDeserializer;
  private String valueDeserializer;
  private String groupId;
  private String autoOffsetReset;
  private String clientId;

  public String getKeyDeserializer() {
    return keyDeserializer;
  }

  public void setKeyDeserializer(String keyDeserializer) {
    this.keyDeserializer = keyDeserializer;
  }

  public String getValueDeserializer() {
    return valueDeserializer;
  }

  public void setValueDeserializer(String valueDeserializer) {
    this.valueDeserializer = valueDeserializer;
  }

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public String getAutoOffsetReset() {
    return autoOffsetReset;
  }

  public void setAutoOffsetReset(String autoOffsetReset) {
    this.autoOffsetReset = autoOffsetReset;
  }

  public String getClientId() { return clientId; }

  public void setClientId(String clientId) { this.clientId = clientId; }

}
