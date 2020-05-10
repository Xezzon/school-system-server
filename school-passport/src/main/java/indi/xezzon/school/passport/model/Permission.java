package indi.xezzon.school.passport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author xezzon
 */
@Data
public class Permission implements Serializable {
    @JsonSerialize (using = PermissionIdJsonSerializer.class)
    @JsonDeserialize (using = PermissionIdJsonDeserializer.class)
    private Integer id;
    
    @JsonIgnore
    private String resource;
    
    private String description;
    
    private static final long serialVersionUID = 1L;
}

@PropertySource ("classpath:config/hashids.properties")
@Component
class PermissionIdJsonSerializer extends JsonSerializer<Integer> {
    @Value ("${permission}")
    private String salt;
    
    @Override
    public void serialize(Integer integer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString((new Hashids(salt)).encode(integer));
    }
}

@PropertySource ("classpath:config/hashids.properties")
@Component
class PermissionIdJsonDeserializer extends JsonDeserializer<Integer> {
    @Value ("${permission}")
    private String salt;
    
    @Override
    public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return (int)(new Hashids(salt)).decode(jsonParser.getText())[0];
    }
}