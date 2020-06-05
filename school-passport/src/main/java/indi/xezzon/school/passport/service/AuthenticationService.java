package indi.xezzon.school.passport.service;

import indi.xezzon.school.common.model.Account;

/**
 * @author xezzon
 */
public interface AuthenticationService {
    /**
     * 注册
     *
     * @param account 用户名、密码
     */
    void register(Account account);
    
    /**
     * 登陆
     *
     * @param username   用户名
     * @param cipher     密码
     * @param rememberMe 是否记住登陆状态
     *                   由于username、cipher、rememberMe用在了三个不同的地方，所以在设计Service接口时接收三个参数，同时也方便了Controller层
     */
    void login(String username, String cipher, boolean rememberMe);
    
    /**
     * 登出
     */
    void logout();
    
    /**
     * 修改密码
     *
     * @param cipher 新密码
     */
    void modifyCipher(String cipher);
}
