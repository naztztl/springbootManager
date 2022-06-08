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

CREATE TABLE `sys_file` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件名称',
    `type` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件类型',
    `size` bigint(100) DEFAULT NULL COMMENT '文件大小（kb）',
    `url` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '下载链接',
    `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
    `enable` tinyint(1) DEFAULT '1' COMMENT '是否禁用链接',
    `md5` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件md5',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `sys_role` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `ztll`.`sys_menu` (
   `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
   `name` VARCHAR(45) NULL COMMENT '名称',
   `path` VARCHAR(45) NULL COMMENT '路径',
   `icon` VARCHAR(45) NULL COMMENT '图标',
   `description` VARCHAR(45) NULL COMMENT '描述',
   PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;
