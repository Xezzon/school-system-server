package indi.xezzon.school.model;

import indi.xezzon.school.constant.enums.AccountStatusEnum;
import lombok.Data;
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
@Data
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
    
    public Account(String username, String cipher, AccountStatusEnum status, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.username = username;
        this.cipher = cipher;
        this.status = status;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }
}
