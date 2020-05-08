CREATE TABLE `account`
(
    `id`           bigint(20) unsigned                    NOT NULL AUTO_INCREMENT,
    `username`     varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
    `cipher`       char(60) COLLATE utf8mb4_unicode_ci    NOT NULL COMMENT '密码',
    `status`       tinyint(4)                             NOT NULL DEFAULT 0 COMMENT '账号状态',
    `created_time` timestamp                              NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    `updated_time` timestamp                              NOT NULL DEFAULT current_timestamp(),
    PRIMARY KEY (`id`),
    UNIQUE KEY `account_username_uindex` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 24
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='账号'