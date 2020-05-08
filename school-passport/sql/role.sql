CREATE TABLE `role`
(
    `id`   int(10) unsigned                       NOT NULL AUTO_INCREMENT,
    `role` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色',
    PRIMARY KEY (`id`),
    UNIQUE KEY `role_name_uindex` (`role`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci