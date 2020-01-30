package learning.reactor.kafka.json.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperFactory {

  private static ObjectMapper objectMapper;

  private ObjectMapperFactory() {
  }

  public static ObjectMapper getInstance() {
    if (objectMapper == null) {
      buildObjectMapper();
    }
    return objectMapper;
  }

  private static void buildObjectMapper() {
    synchronized (ObjectMapperFactory.class) {
      if (objectMapper == null) {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      }
    }
  }

}
