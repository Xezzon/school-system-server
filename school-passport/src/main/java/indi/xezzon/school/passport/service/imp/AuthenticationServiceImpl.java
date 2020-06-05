package indi.xezzon.school.passport.service.imp;

import cn.hutool.crypto.digest.BCrypt;
import indi.xezzon.school.common.constant.enums.AccountStatusEnum;
import indi.xezzon.school.common.model.Account;
import indi.xezzon.school.passport.repository.AccountMapper;
import indi.xezzon.school.passport.service.AuthenticationService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author xezzon
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AccountMapper mapper;
    private final CipherHashHandler hashHandler;
    
    @Autowired
    public AuthenticationServiceImpl(AccountMapper mapper, CipherHashHandler hashHandler) {
        this.mapper = mapper;
        this.hashHandler = hashHandler;
    }
    
    @Override
    public void register(Account account) {
        account.setCipher(hashHandler.hash(account.getCipher()));
        account.setStatus(AccountStatusEnum.NORMAL);
        LocalDateTime now = LocalDateTime.now();
        account.setCreatedTime(now);
        account.setUpdatedTime(now);
        mapper.insert(account);
    }
    
    @Override
    public void login(String username, String cipher, boolean rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, cipher, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
    }
    
    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
    
    @Override
    public void modifyCipher(String cipher) {
        Subject subject = SecurityUtils.getSubject();
        Long accountId = (Long)subject.getPrincipal();
        Account account = new Account();
        account.setId(accountId);
        account.setCipher(hashHandler.hash(cipher));
        mapper.updateByPrimaryKey(account);
    }
}

/**
 * 注册时，对密码进行加密
 *
 * @author xezzon
 */
@PropertySource ("classpath:config/passport.properties")
@Component
class CipherHashHandler {
    @Value ("${bcrypt.hash-times:10}")
    private int hashTimes;
    
    public String hash(String plaintext) {
        return BCrypt.hashpw(plaintext, BCrypt.gensalt(hashTimes));
    }
}
