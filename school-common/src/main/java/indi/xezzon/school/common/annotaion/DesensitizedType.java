package indi.xezzon.school.common.annotaion;

import cn.hutool.core.util.DesensitizedUtil;

import java.lang.annotation.*;

/**
 * @author xezzon
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DesensitizedType {
    DesensitizedUtil.DesensitizedType value() default DesensitizedUtil.DesensitizedType.USER_ID;
}
