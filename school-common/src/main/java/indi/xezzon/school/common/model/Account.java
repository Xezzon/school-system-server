package indi.xezzon.school.common.model;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author xezzon
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Account extends BaseEntity {
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String cipher;

    private static final long serialVersionUID = 1L;

    public Account(String username, String cipher) {
        this.username = username;
        this.cipher = cipher;
    }
}
