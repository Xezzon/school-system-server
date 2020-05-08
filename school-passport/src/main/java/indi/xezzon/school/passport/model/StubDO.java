package indi.xezzon.school.passport.model;

import indi.xezzon.school.passport.constant.enums.AccountStatusEnum;
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
