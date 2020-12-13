package indi.xezzon.school.jwc.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.BCrypt;
import indi.xezzon.school.common.model.Account;
import indi.xezzon.school.jwc.repository.AccountMapper;
import indi.xezzon.school.jwc.service.AuthenticationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xezzon
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AccountMapper accountMapper;

    @Autowired
    public AuthenticationServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public void register(String username, String cipher) {
        cipher = BCrypt.hashpw(cipher, BCrypt.gensalt());
        Account account = new Account(username, cipher);
        accountMapper.insert(account);
    }

    @Override
    public void login(String username, String cipher) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, cipher);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
    }

    @Override
    public long getCurrentAccountId() {
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        return ObjectUtil.isNotNull(account) ? account.getId() : -1L;
    }
}
