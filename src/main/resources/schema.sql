DROP DATABASE IF EXISTS ztll;
CREATE DATABASE ztll CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ztll;
CREATE TABLE `sys_user` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `username` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
    `password` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
    `nickname` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
    `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
    `phone` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
    `address` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
    `creat_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `avatar_url` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
