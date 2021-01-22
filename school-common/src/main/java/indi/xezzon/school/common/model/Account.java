package indi.xezzon.school.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xezzon
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Account implements Serializable {
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String cipher;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private static final long serialVersionUID = 1L;

    public Account(String username, String cipher) {
        this.username = username;
        this.cipher = cipher;
    }
}
