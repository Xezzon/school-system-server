package indi.xezzon.school.jwc.repository;

import indi.xezzon.school.common.model.Account;
import org.springframework.stereotype.Repository;

/**
 * @author xezzon
 */
@Repository
public interface AccountMapper {
    /**
     * 新增账号
     *
     * @param account 账号信息。其中用户名和密码为必填字段。
     * @return 新增账号的id
     */
    Long insert(Account account);
}
