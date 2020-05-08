CREATE TABLE `account_role`
(
    `account_id` bigint(20) unsigned NOT NULL,
    `role_id`    int(10) unsigned    NOT NULL,
    PRIMARY KEY (`account_id`, `role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci