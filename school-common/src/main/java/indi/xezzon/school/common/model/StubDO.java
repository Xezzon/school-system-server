package indi.xezzon.school.common.model;

import indi.xezzon.school.common.constant.enums.AccountStatusEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xezzon
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class StubDO implements Serializable {
    private Long id;

    private String cipher;

    private AccountStatusEnum status;
}
