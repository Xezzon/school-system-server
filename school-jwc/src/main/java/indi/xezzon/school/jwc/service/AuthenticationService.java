package indi.xezzon.school.jwc.service;

import indi.xezzon.school.common.model.Account;

/**
 * @author xezzon
 */
public interface AuthenticationService {
    /**
     * 注册
     * @param username 用户名
     * @param cipher 密码
     */
    void register(String username, String cipher);

    /**
     * 登录
     * @param username 用户名
     * @param cipher 密码
     */
    void login(String username, String cipher);

    /**
     * 当前账号
     * @return 当前账号
     */
    Account getCurrentAccount();

    /**
     * 获取当前会话的用户ID
     * @return 用户ID,若未登录则返回-1
     */
    long getCurrentAccountId();
}
