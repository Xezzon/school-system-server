package indi.xezzon.school.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.Set;

/**
 * @author xezzon
 */
@Data
public class Role implements Serializable {
    @JsonSerialize (using = RoleIdJsonSerializer.class)
    @JsonDeserialize (using = RoleIdJsonDeserializer.class)
    private Integer id;
    
    @JsonIgnore
    private String name;
    
    private String description;
    
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private Set<Permission> permissions;
    
    private static final long serialVersionUID = 1L;
}

@PropertySource ("classpath:config/hashids.properties")
@Component
class RoleIdJsonSerializer extends JsonSerializer<Integer> {
    @Value ("${role}")
    private String salt;
    
    @Override
    public void serialize(Integer integer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(HashidsUtil.encode(integer, salt));
    }
}

@PropertySource ("classpath:config/hashids.properties")
@Component
class RoleIdJsonDeserializer extends JsonDeserializer<Integer> {
    @Value ("${role}")
    private String salt;
    
    @Override
    public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return (int)HashidsUtil.decode(jsonParser.getText(), salt);
    }
}