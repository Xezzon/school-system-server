package indi.xezzon.school.common.model;

import cn.hutool.core.util.ObjectUtil;
import lombok.*;

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
}
