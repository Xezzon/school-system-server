package indi.xezzon.school.passport.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author xezzon
 */
@Data
public class Role implements Serializable {
    private Integer id;
    
    private String name;
    
    private String description;
    
    private Set<Permission> permissions;
    
    private static final long serialVersionUID = 1L;
}