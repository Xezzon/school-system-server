package indi.xezzon.school.common.model;

import indi.xezzon.school.common.constant.enums.GenderEnum;
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
public class Student extends BaseEntity {
    private Long accountId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 所属班级
     */
    private Long klasoId;

    /**
     * 性别
     */
    private GenderEnum gender;

    /**
     * 手机号
     */
    private String mobile;
}
