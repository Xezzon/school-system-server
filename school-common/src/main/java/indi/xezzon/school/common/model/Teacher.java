package indi.xezzon.school.common.model;

import cn.hutool.core.util.DesensitizedUtil;
import indi.xezzon.school.common.annotaion.DesensitizedType;
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
public class Teacher extends BaseEntity {
    private Long accountId;

    /**
     * 教师姓名
     */
    @DesensitizedType(DesensitizedUtil.DesensitizedType.CHINESE_NAME)
    private String name;

    /**
     * 性别
     */
    private GenderEnum gender;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 简介
     */
    private String readme;

    private static final long serialVersionUID = 1L;
}
