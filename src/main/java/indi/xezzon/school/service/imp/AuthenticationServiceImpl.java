package indi.xezzon.school.service.imp;

import cn.hutool.crypto.digest.BCrypt;
import indi.xezzon.school.constant.enums.AccountStatusEnum;
import indi.xezzon.school.model.Account;
import indi.xezzon.school.repository.AccountMapper;
import indi.xezzon.school.service.AuthenticationService;
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
    public void register(String username, String cipher) {
        Account account = new Account(username, hashHandler.hash(cipher), AccountStatusEnum.NORMAL, LocalDateTime.now(), LocalDateTime.now());
        mapper.insert(account);
    }
}

@PropertySource("classpath:config/passport.properties")
@Component
class CipherHashHandler {
    @Value("${bcrypt.hash-times:10}")
    private int hashTimes;
    
    public String hash(String plaintext) {
        return BCrypt.hashpw(plaintext, BCrypt.gensalt(hashTimes));
    }
}
