package indi.xezzon.school.common.model;

import indi.xezzon.school.common.constant.enums.AccountStatusEnum;

import java.io.Serializable;

/**
 * @author xezzon
 */
public class StubDO implements Serializable {
    private Long id;
    
    private String cipher;
    
    private AccountStatusEnum status;
    
    public StubDO() {
        super();
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCipher() {
        return cipher;
    }
    
    public void setCipher(String cipher) {
        this.cipher = cipher;
    }
    
    public AccountStatusEnum getStatus() {
        return status;
    }
    
    public void setStatus(AccountStatusEnum status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "StubDO{" + "id=" + id + ", cipher='" + cipher + '\'' + ", status=" + status + '}';
    }
}
