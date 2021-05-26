package indi.xezzon.school.auth.config;

import cn.hutool.crypto.digest.BCrypt;
import indi.xezzon.school.auth.repository.AccountMapper;
import indi.xezzon.school.auth.repository.AccountRoleRelMapper;
import indi.xezzon.school.auth.repository.RolePermissionMapper;
import indi.xezzon.school.common.model.Account;
import indi.xezzon.school.common.model.Permission;
import indi.xezzon.school.common.model.Role;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xezzon
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(normalRealm());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public Realm normalRealm() {
        NormalRealm realm = new NormalRealm();
        realm.setCredentialsMatcher(credentialsMatcher());
        return realm;
    }

    @Bean
    public CredentialsMatcher credentialsMatcher() {
        return new BcryptCredentialMatcher();
    }

    @Bean
    public SessionManager sessionManager() {
        return new ServletContainerSessionManager();
    }
}

class NormalRealm extends AuthorizingRealm {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountRoleRelMapper accountRoleRelMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();

        Account account = accountMapper.queryByUsername(username);
        String cipher = account.getCipher();
        // 脱敏处理
        account.setCipher(null);
        return new SimpleAuthenticationInfo(account, cipher, this.getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Account account = (Account) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        Set<Role> roles = accountRoleRelMapper.queryRoleByAccountId(account.getId());
        Set<Permission> permissions = roles.parallelStream()
                .map(role -> rolePermissionMapper.queryPermissionByRole(role))
                .flatMap(Collection::parallelStream)
                .collect(Collectors.toSet());
        authorizationInfo.setRoles(roles.parallelStream().map(Role::toString).collect(Collectors.toSet()));
        authorizationInfo.setRoles(permissions.parallelStream().map(Permission::toString).collect(Collectors.toSet()));

        return authorizationInfo;
    }
}

class BcryptCredentialMatcher implements CredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        boolean check = false;
        if (authenticationToken instanceof  UsernamePasswordToken) {
            check = BCrypt.checkpw(String.valueOf(((UsernamePasswordToken) authenticationToken).getPassword()), String.valueOf(authenticationInfo.getCredentials()));
        }
        return check;
    }
}
