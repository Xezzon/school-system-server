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

/**
 * @author xezzon
 */
@Getter
@Setter
@ToString
public class Teacher implements Serializable {
    @JsonSerialize(using = TeacherIdSerializer.class)
    @JsonDeserialize(using = TeacherIdDeserializer.class)
    private Long id;

    /**
     * 教师姓名
     */
    private String name;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 简介
     */
    private String readme;

    private static final long serialVersionUID = 1L;
}

@PropertySource("classpath:config/hashids.properties")
@Component
class TeacherIdSerializer extends JsonSerializer<Long> {
    @Value("${teacher:rehc}")
    private String salt;

    @Override
    public void serialize(Long id, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(HashidsUtil.encode(id, salt));
    }
}

@PropertySource("classpath:config/hashids.properties")
@Component
class TeacherIdDeserializer extends JsonDeserializer<Long> {
    @Value("${teacher:rehc}")
    private String salt;

    @Override
    public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return HashidsUtil.decode(jsonParser.getText(), salt);
    }
}
