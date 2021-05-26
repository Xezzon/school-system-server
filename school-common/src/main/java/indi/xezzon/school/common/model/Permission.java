package indi.xezzon.school.common.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @author xezzon
 */
@Getter
@Setter
public class Permission extends BaseEntity {
    private Long id;
    /**
     * 资源路径
     */
    private String resource;
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 资源操作
     */
    private String operator;

    @Override
    public String toString() {
        return resource + ":" + operator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Permission that = (Permission) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
