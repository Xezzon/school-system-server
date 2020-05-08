CREATE TABLE `role_permission`
(
    `role_id`       int(10) unsigned NOT NULL,
    `permission_id` int(10) unsigned NOT NULL,
    PRIMARY KEY (`role_id`, `permission_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci