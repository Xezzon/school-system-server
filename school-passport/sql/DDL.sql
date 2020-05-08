CREATE TABLE `account`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `username`     varchar(20)         NOT NULL COMMENT '用户名',
    `cipher`       char(60)            NOT NULL COMMENT '密码',
    `status`       tinyint(4)          NOT NULL DEFAULT 0 COMMENT '账号状态',
    `created_time` timestamp           NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    `updated_time` timestamp           NOT NULL DEFAULT current_timestamp(),
    PRIMARY KEY (`id`),
    UNIQUE KEY `account_username_uindex` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 24
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='账号';

CREATE TABLE `role`
(
    `id`   int(10) unsigned NOT NULL AUTO_INCREMENT,
    `role` varchar(32)      NOT NULL COMMENT '角色',
    PRIMARY KEY (`id`),
    UNIQUE KEY `role_name_uindex` (`role`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `account_role`
(
    `account_id` bigint(20) unsigned NOT NULL,
    `role_id`    int(10) unsigned    NOT NULL,
    PRIMARY KEY (`account_id`, `role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `permission`
(
    `id`         int(10) unsigned NOT NULL AUTO_INCREMENT,
    `permission` varchar(64)      NOT NULL COMMENT '权限描述',
    PRIMARY KEY (`id`),
    UNIQUE KEY `permission_permission_uindex` (`permission`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `role_permission`
(
    `role_id`       int(10) unsigned NOT NULL,
    `permission_id` int(10) unsigned NOT NULL,
    PRIMARY KEY (`role_id`, `permission_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;