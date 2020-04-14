package indi.xezzon.school.service;

import indi.xezzon.school.model.Account;

/**
 * @author xezzon
 */
public interface AuthenticationService {
    /**
     * 注册
     * @param account 用户名、密码
     */
    void register(Account account);
}
