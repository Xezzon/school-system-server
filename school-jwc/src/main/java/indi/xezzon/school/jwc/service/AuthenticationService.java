package indi.xezzon.school.jwc.service;

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
}
