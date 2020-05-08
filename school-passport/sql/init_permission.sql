# 生成系统管理员账号，初始密码为“xezzon”，请务必修改cipher的值。cipher值是经过bcrypt加密后的，可通过 https://www.jisuan.mobi/p163u3BN66Hm6JWx.html 进行在线加密
INSERT INTO account(id, username, cipher)
    VALUE (0, '000001', '$2a$10$zj7syx2pwBNX8MAzuMd29.QF/9ZG954Cd2yXd4UXWIjGkyjI5TsHq');

# 生成系统管理员角色并赋值给上述用户
INSERT INTO role(id, name, description) VALUE (0, 'root', '系统管理员');
INSERT INTO account_role(account_id, role_id) VALUE (0, 0);

# 添加并赋予系统管理员的权限
INSERT INTO permission(resource, description)
VALUES ('log:read', '查看日志');
INSERT INTO role_permission(role_id, permission_id)
SELECT rp.role_id, permission.id
FROM permission
         CROSS JOIN
         (SELECT 0 AS role_id) AS rp;

# 添加剩余的所有权限
INSERT INTO permission(resource, description)
VALUES ();
