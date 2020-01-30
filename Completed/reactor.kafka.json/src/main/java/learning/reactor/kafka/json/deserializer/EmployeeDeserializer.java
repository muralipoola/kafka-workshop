package learning.reactor.kafka.json.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import learning.reactor.kafka.json.config.ObjectMapperFactory;
import learning.reactor.kafka.json.model.Employee;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeDeserializer implements Deserializer {

  private static final Logger log = LoggerFactory.getLogger(EmployeeDeserializer.class);

  @Override
  public Employee deserialize(String topic, byte[] data) {
    if (data != null) {
      try {
        ObjectMapper objectMapper = ObjectMapperFactory.getInstance();
        return objectMapper.readValue(data, Employee.class);
      } catch (IOException e) {
        log.error("Failed to deserialize with {}", e.getMessage(), e);
      }
    }
    return null;
  }
}
