package indi.xezzon.school.common.model;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author xezzon
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Role extends BaseEntity {
    private Long id;

    private String name;

    private static final long serialVersionUID = 1L;
}
