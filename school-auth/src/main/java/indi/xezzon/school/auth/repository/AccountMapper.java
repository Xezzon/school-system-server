package indi.xezzon.school.auth.repository;

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
     */
    void insert(Account account);

    /**
     * 通过用户名查询账号
     *
     * @param username 用户名
     * @return 账号信息
     */
    Account selectByUsername(String username);
}
