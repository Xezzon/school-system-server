package indi.xezzon.school.common.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * @author xezzon
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Role extends BaseEntity {
    private Long id;

    private String name;

    private Department department;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (department != null) {
            sb.append(department).append(":");
        }
        sb.append(name);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return id.equals(role.id) && department.equals(role.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, department);
    }
}
