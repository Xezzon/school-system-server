package indi.xezzon.school.common.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xezzon
 */
@Getter
@Setter
public abstract class BaseEntity implements Serializable {
    protected LocalDateTime createTime;
    protected LocalDateTime updateTime;
}
