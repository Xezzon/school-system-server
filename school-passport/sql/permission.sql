CREATE TABLE `permission`
(
    `id`         int(10) unsigned                       NOT NULL AUTO_INCREMENT,
    `permission` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限描述',
    PRIMARY KEY (`id`),
    UNIQUE KEY `permission_permission_uindex` (`permission`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci