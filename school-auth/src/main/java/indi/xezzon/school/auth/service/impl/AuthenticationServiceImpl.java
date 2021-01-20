package indi.xezzon.school.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.BCrypt;
import indi.xezzon.school.auth.repository.AccountMapper;
import indi.xezzon.school.auth.repository.AccountRoleRelMapper;
import indi.xezzon.school.auth.service.AuthenticationService;
import indi.xezzon.school.common.model.Account;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xezzon
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AccountMapper accountMapper;
    private final AccountRoleRelMapper accountRoleRelMapper;

    @Autowired
    public AuthenticationServiceImpl(AccountMapper accountMapper, AccountRoleRelMapper accountRoleRelMapper) {
        this.accountMapper = accountMapper;
        this.accountRoleRelMapper = accountRoleRelMapper;
    }

    @Override
    public long register(String username, String cipher) {
        cipher = BCrypt.hashpw(cipher, BCrypt.gensalt());
        Account account = new Account(username, cipher);
        accountMapper.insert(account);
        return account.getId();
    }

    @Override
    @Transactional(rollbackFor = PersistenceException.class)
    public void register(String username, String cipher, long roleId) {
        long accountId = register(username, cipher);
        accountRoleRelMapper.insert(accountId, roleId);
    }

    @Override
    public void login(String username, String cipher) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, cipher);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
    }

    @Override
    public Account getCurrentAccount() {
        return (Account) SecurityUtils.getSubject().getPrincipal();
    }

    @Override
    public long getCurrentAccountId() {
        Account account = getCurrentAccount();
        return ObjectUtil.isNotNull(account) ? account.getId() : -1L;
    }
}
