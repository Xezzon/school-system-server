create table account
(
    id bigint unsigned auto_increment
        primary key,
    username varchar(20) not null comment '用户名',
    cipher char(60) not null comment '密码',
    status tinyint not null comment '账号状态',
    created_time timestamp default current_timestamp() not null on update current_timestamp(),
    updated_time timestamp default '0000-00-00 00:00:00' not null,
    constraint account_username_uindex
        unique (username)
)
    comment '账号';

