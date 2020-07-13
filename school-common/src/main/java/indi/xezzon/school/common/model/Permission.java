package indi.xezzon.school.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import indi.xezzon.school.common.util.HashidsUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author xezzon
 */
public class Permission implements Serializable {
    @JsonSerialize (using = PermissionIdJsonSerializer.class)
    @JsonDeserialize (using = PermissionIdJsonDeserializer.class)
    private Integer id;
    
    @JsonIgnore
    private String resource;
    
    private String description;
    
    private static final long serialVersionUID = 1L;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getResource() {
        return resource;
    }
    
    public void setResource(String resource) {
        this.resource = resource;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return "Permission{" + "id=" + id + ", resource='" + resource + '\'' + ", description='" + description + '\'' + '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Permission)) {
            return false;
        }
        Permission that = (Permission)o;
        return Objects.equals(id, that.id) && Objects.equals(resource, that.resource);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, resource);
    }
}

@PropertySource ("classpath:config/hashids.properties")
@Component
class PermissionIdJsonSerializer extends JsonSerializer<Integer> {
    @Value ("${permission}")
    private String salt;
    
    @Override
    public void serialize(Integer integer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(HashidsUtil.encode(integer, salt));
    }
}

@PropertySource ("classpath:config/hashids.properties")
@Component
class PermissionIdJsonDeserializer extends JsonDeserializer<Integer> {
    @Value ("${permission}")
    private String salt;
    
    @Override
    public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return Math.toIntExact(HashidsUtil.decode(jsonParser.getText(), salt));
    }
}