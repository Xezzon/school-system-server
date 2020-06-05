package indi.xezzon.school.passport.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import indi.xezzon.school.common.model.Permission;
import indi.xezzon.school.common.model.Role;
import indi.xezzon.school.passport.repository.PermissionMapper;
import indi.xezzon.school.passport.repository.RoleMapper;
import indi.xezzon.school.passport.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xezzon
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    private final RoleMapper roleMapper;
    private final PermissionMapper permissionMapper;
    
    @Autowired
    public AuthorizationServiceImpl(RoleMapper roleMapper, PermissionMapper permissionMapper) {
        this.roleMapper = roleMapper;
        this.permissionMapper = permissionMapper;
    }
    
    @Override
    public PageInfo<Role> listRole(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> roles = roleMapper.list();
        return new PageInfo<>(roles);
    }
    
    @Override
    public List<Permission> listPermission() {
        return permissionMapper.list();
    }
    
    @Override
    public void addRole(Role role) {
        roleMapper.insert(role);
        Integer roleId = role.getId();
        Set<Permission> permissions = role.getPermissions();
        Set<Integer> permissionsId = permissions.stream().map(Permission::getId).collect(Collectors.toSet());
        permissionMapper.insertRolePermissionRel(roleId, permissionsId);
    }
}
