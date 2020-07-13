package indi.xezzon.school.common.model;

import indi.xezzon.school.common.constant.enums.AccountStatusEnum;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 账号
 *
 * @author xezzon
 */
public class Account implements Serializable {
    private Long id;
    
    /**
     * 用户名（即学号）
     */
    @NotBlank
    private String username;
    
    /**
     * 口令,长度为6-64。加密后长度为60。
     */
    @Length (min = 6, max = 64)
    private String cipher;
    
    /**
     * 账号状态
     */
    private AccountStatusEnum status;
    
    @DateTimeFormat (pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;
    
    @DateTimeFormat (pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;
    
    private static final long serialVersionUID = 1L;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
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
    
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }
    
    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
    
    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }
    
    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }
    
    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", username='" + username + '\'' + ", cipher='" + cipher + '\'' + ", status=" + status + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + '}';
    }
}
