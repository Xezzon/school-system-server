package indi.xezzon.school.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xezzon
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Role implements Serializable {
    private Long id;

    private String name;

    private static final long serialVersionUID = 1L;
}
