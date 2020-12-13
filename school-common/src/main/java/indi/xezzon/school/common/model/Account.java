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
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class Account implements Serializable {
    @JsonSerialize(using = AccountIdSerializer.class)
    @JsonDeserialize(using = AccountIdDeserializer.class)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String cipher;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private static final long serialVersionUID = 1L;

    public Account(String username, String cipher) {
        this.username = username;
        this.cipher = cipher;
    }
}

@PropertySource("classpath:config/hashids.properties")
@Component
class AccountIdSerializer extends JsonSerializer<Long> {
    @Value("${account:tnuo}")
    private String salt;

    @Override
    public void serialize(Long id, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(HashidsUtil.encode(id, salt));
    }
}

@PropertySource("classpath:config/hashids.properties")
@Component
class AccountIdDeserializer extends JsonDeserializer<Long> {
    @Value("${account:tnuo}")
    private String salt;

    @Override
    public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return HashidsUtil.decode(jsonParser.getText(), salt);
    }
}
