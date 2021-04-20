package indi.xezzon.school.auth.repository;

import indi.xezzon.school.common.model.Account;
import indi.xezzon.school.common.repository.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author xezzon
 */
@Repository
public interface AccountMapper extends BaseMapper<Account> {
    /**
     * 通过用户名查询账号
     *
     * @param username 用户名
     * @return 账号信息
     */
    Account queryByUsername(String username);
}
