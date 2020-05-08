package indi.xezzon.school.passport.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xezzon
 */
@Data
public class Permission implements Serializable {
    private Long id;
    
    private String resource;
    
    private String description;
    
    private static final long serialVersionUID = 1L;
}