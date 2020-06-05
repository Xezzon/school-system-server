package indi.xezzon.school.common.model;

import indi.xezzon.school.common.constant.enums.AccountStatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xezzon
 */
@Data
public class StubDO implements Serializable {
    private Long id;
    
    private String cipher;
    
    private AccountStatusEnum status;
}