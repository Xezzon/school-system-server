package indi.xezzon.school.common.model;

import cn.hutool.core.util.ObjectUtil;
import lombok.*;

import java.util.Objects;

/**
 * @author xezzon
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department extends BaseEntity {
    private Long id;

    private String name;

    /**
     * 上级组织
     */
    private Department superior;

    /**
     * 标签
     */
    private String etikedo;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (ObjectUtil.isNull(this.superior)) {
            sb.append("/").append(name);
        } else {
            sb.append(this.superior).append("/").append(name);
        }
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
        Department that = (Department) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
