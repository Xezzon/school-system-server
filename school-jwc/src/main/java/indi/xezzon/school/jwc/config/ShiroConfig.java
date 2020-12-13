package indi.xezzon.school.jwc.config;

import cn.hutool.crypto.digest.BCrypt;
import indi.xezzon.school.common.model.Account;
import indi.xezzon.school.jwc.repository.AccountMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xezzon
 */
@Configuration
public class ShiroConfig {
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(normalRealm());
        return securityManager;
    }
    
    @Bean
    public NormalRealm normalRealm() {
        NormalRealm realm = new NormalRealm();
        realm.setCredentialsMatcher(credentialsMatcher());
        return realm;
    }

    @Bean
    public CredentialsMatcher credentialsMatcher() {
        return new BcryptCredentialMatcher();
    }
}

class NormalRealm extends AuthorizingRealm {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();

        Account account = accountMapper.selectByUsername(username);
        String cipher = account.getCipher();
        // 脱敏处理
        account.setCipher(null);
        return new SimpleAuthenticationInfo(account, cipher, this.getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}

class BcryptCredentialMatcher implements CredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        return BCrypt.checkpw(String.valueOf(((UsernamePasswordToken)authenticationToken).getPassword()), String.valueOf(authenticationInfo.getCredentials()));
    }
}
