package indi.xezzon.school.common.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import indi.xezzon.school.common.util.HashidsUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xezzon
 */
@Getter
@Setter
@ToString
public class Course implements Serializable {
    @JsonSerialize(using = CourseIdSerializer.class)
    @JsonDeserialize(using = CourseIdDeserializer.class)
    private Long id;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 学分
     */
    private String credit;

    /**
     * 执教老师
     */
    private Teacher teacher;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;
}

@PropertySource("classpath:config/hashids.properties")
@Component
class CourseIdSerializer extends JsonSerializer<Long> {
    @Value("${course:esr}")
    private String salt;

    @Override
    public void serialize(Long id, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(HashidsUtil.encode(id, salt));
    }
}

@PropertySource("classpath:config/hashids.properties")
@Component
class CourseIdDeserializer extends JsonDeserializer<Long> {
    @Value("${course:esr}")
    private String salt;

    @Override
    public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return HashidsUtil.decode(jsonParser.getText(), salt);
    }
}
