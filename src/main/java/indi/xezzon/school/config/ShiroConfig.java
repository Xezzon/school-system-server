package indi.xezzon.school.config;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import indi.xezzon.school.constant.enums.AccountStatusEnum;
import indi.xezzon.school.model.StubDO;
import indi.xezzon.school.repository.AccountMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.UUID;

/**
 * Shiro配置，主要是过滤器和SecurityManager
 * @author xezzon
 */
@Configuration
public class ShiroConfig {
    /**
     * @return 权限过滤器
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
    
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
    
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    
    @Bean
    public CredentialsMatcher credentialsMatcher() {
        return new BcryptPasswordMatcher();
    }
    
    @Bean
    public ShiroRealm shiroRealm(CredentialsMatcher credentialsMatcher) {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(credentialsMatcher);
        return shiroRealm;
    }
    
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new TokenSessionManager();
        sessionManager.setSessionIdCookieEnabled(false);
        return sessionManager;
    }
    
    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        // 记住登陆状态10天
        cookie.setMaxAge(10 * 24 * 60 * 60);
        return cookie;
    }
    
    @Bean
    public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie) {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(rememberMeCookie);
        return rememberMeManager;
    }
    
    @Bean
    public DefaultWebSecurityManager securityManager(ShiroRealm shiroRealm, SessionManager sessionManager, RememberMeManager rememberMeManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);
        securityManager.setSessionManager(sessionManager);
        securityManager.setRememberMeManager(rememberMeManager);
        return securityManager;
    }
}

class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private AccountMapper accountMapper;
    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        StubDO stub = accountMapper.selectStubByUsername(token.getUsername());
    
        if (ObjectUtil.isNull(stub)) {
            throw new UnknownAccountException("用户不存在");
        }
        if (stub.getStatus() == AccountStatusEnum.LOCKED) {
            throw new LockedAccountException("账号已被锁定");
        }
        
        return new SimpleAuthenticationInfo(stub.getId(), stub.getCipher(), this.getName());
    }
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}

/**
 * 使用token替代cookie作为会话管理的媒介，避开CSRF攻击
 */
class TokenSessionManager extends DefaultWebSessionManager {
    @Override
    protected Serializable getSessionId(ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("X-XSRF-TOKEN");
        if(StrUtil.isBlank(token)){
            token = UUID.randomUUID().toString();
        }
    
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroHttpServletRequest.URL_SESSION_ID_SOURCE);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
        return token;
    }
}

/**
 * Bcrypt密码验证器
 */
class BcryptPasswordMatcher implements CredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String password = String.valueOf(token.getPassword());
        String credentials = String.valueOf(authenticationInfo.getCredentials());
        return BCrypt.checkpw(password, credentials);
    }
}
