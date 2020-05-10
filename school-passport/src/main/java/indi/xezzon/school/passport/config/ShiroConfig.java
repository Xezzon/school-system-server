package indi.xezzon.school.passport.config;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import indi.xezzon.school.passport.constant.enums.AccountStatusEnum;
import indi.xezzon.school.passport.model.Permission;
import indi.xezzon.school.passport.model.Role;
import indi.xezzon.school.passport.model.StubDO;
import indi.xezzon.school.passport.repository.AccountMapper;
import indi.xezzon.school.passport.repository.PermissionMapper;
import indi.xezzon.school.passport.repository.RoleMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
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
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Shiro配置，主要是过滤器和SecurityManager
 *
 * @author xezzon
 */
@Configuration
public class ShiroConfig {
    /**
     * @return 权限过滤器
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    
    @Bean
    public CredentialsMatcher credentialsMatcher() {
        return new BcryptPasswordMatcher();
    }
    
    @Bean
    public NormalRealm normalRealm() {
        NormalRealm normalRealm = new NormalRealm();
        normalRealm.setCredentialsMatcher(credentialsMatcher());
        return normalRealm;
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
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(rememberMeCookie());
        return rememberMeManager;
    }
    
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(normalRealm());
        securityManager.setSessionManager(sessionManager());
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }
}

class NormalRealm extends AuthorizingRealm {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    
    private static final String ROOT_ACCOUNT_ROLE = "root";
    
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
        Long accountId = (Long)principalCollection.getPrimaryPrincipal();
        
        // 查询角色
        Set<Role> roles = roleMapper.selectByAccountId(accountId);
        // 使用流式编程取出roles的name字段
        Set<String> rolesName = roles.stream().map(Role::getName).collect(Collectors.toSet());
        
        // 查询权限
        Set<Permission> permissions = new HashSet<>();
        // 系统管理员权限单查
        if (rolesName.contains(ROOT_ACCOUNT_ROLE)) {
            permissions.addAll(permissionMapper.listRootPermission());
        }
        permissions.addAll(permissionMapper.selectByRoles(roles));
        // 使用流式编程的方式取出permissions的resource字段
        Set<String> permissionsResource = permissions.stream().map(Permission::getResource).collect(Collectors.toSet());
        
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(rolesName);
        info.setStringPermissions(permissionsResource);
        return info;
    }
}

/**
 * 使用token替代cookie作为会话管理的媒介，避开CSRF攻击
 */
class TokenSessionManager extends DefaultWebSessionManager {
    @Override
    protected Serializable getSessionId(ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String token = request.getHeader("X-XSRF-TOKEN");
        if (StrUtil.isBlank(token)) {
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
