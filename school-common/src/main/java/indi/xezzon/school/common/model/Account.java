package indi.xezzon.school.common.model;

import indi.xezzon.school.common.constant.enums.AccountStatusEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
@Getter
@Setter
@ToString
@EqualsAndHashCode
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
    @Length(min = 6, max = 64)
    private String cipher;

    /**
     * 账号状态
     */
    private AccountStatusEnum status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;

    private static final long serialVersionUID = 1L;
}
