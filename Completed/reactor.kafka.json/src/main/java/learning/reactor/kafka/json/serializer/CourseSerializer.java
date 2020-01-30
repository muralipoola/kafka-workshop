package learning.reactor.kafka.json.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import learning.reactor.kafka.json.config.ObjectMapperFactory;
import learning.reactor.kafka.json.model.Course;
import org.apache.kafka.common.serialization.Serializer;

public class CourseSerializer implements Serializer<Course> {

  @Override
  public byte[] serialize(String topic, Course data) {
    if (data != null) {
      try {
        ObjectMapper objectMapper = ObjectMapperFactory.getInstance();
        return objectMapper.writeValueAsBytes(data);
      } catch (IOException e) {
//        log.error("Failed to serialize with {}", e.getMessage(), e);
      }
    }
    return new byte[0];
  }

}
