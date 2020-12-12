package indi.xezzon.school.jwc.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import indi.xezzon.school.common.model.Account;
import indi.xezzon.school.jwc.repository.AccountMapper;
import indi.xezzon.school.jwc.service.AuthenticationService;
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
}
