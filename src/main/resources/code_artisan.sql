/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : 127.0.0.1
 Source Database       : code_artisan

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : utf-8

 Date: 11/02/2017 22:52:23 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `file`
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `f_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `file_name` varchar(45) NOT NULL COMMENT '文件名',
  `file_url` varchar(45) NOT NULL COMMENT '文件路径',
  PRIMARY KEY (`f_id`),
  UNIQUE KEY `f_id_UNIQUE` (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件表';

-- ----------------------------
--  Table structure for `file_permission`
-- ----------------------------
DROP TABLE IF EXISTS `file_permission`;
CREATE TABLE `file_permission` (
  `f_id` int(11) NOT NULL COMMENT '文件主键id',
  `p_id` int(11) NOT NULL COMMENT '权限主键id',
  KEY `f_id_idx` (`f_id`),
  KEY `p_id_idx` (`p_id`),
  CONSTRAINT `f_id` FOREIGN KEY (`f_id`) REFERENCES `file` (`f_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `p_id_3` FOREIGN KEY (`p_id`) REFERENCES `permission` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限文件关联表';

-- ----------------------------
--  Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `m_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_name` varchar(45) NOT NULL COMMENT '菜单名称',
  `menu_url` varchar(45) NOT NULL COMMENT '菜单URL',
  `parent_m_id` int(11) DEFAULT NULL COMMENT '父级菜单',
  PRIMARY KEY (`m_id`),
  UNIQUE KEY `m_id_UNIQUE` (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
--  Table structure for `menu_permission`
-- ----------------------------
DROP TABLE IF EXISTS `menu_permission`;
CREATE TABLE `menu_permission` (
  `m_id` int(11) NOT NULL COMMENT '菜单主键',
  `p_id` int(11) NOT NULL COMMENT '权限主键',
  KEY `p_id_idx` (`p_id`),
  KEY `m_id_idx` (`m_id`),
  CONSTRAINT `m_id` FOREIGN KEY (`m_id`) REFERENCES `menu` (`m_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `p_id` FOREIGN KEY (`p_id`) REFERENCES `permission` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单和权限关联表';

-- ----------------------------
--  Table structure for `operation`
-- ----------------------------
DROP TABLE IF EXISTS `operation`;
CREATE TABLE `operation` (
  `o_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `o_name` varchar(45) NOT NULL COMMENT '操作名称',
  `o_code` varchar(45) NOT NULL COMMENT '操作编码',
  `url` varchar(45) NOT NULL COMMENT '拦截的url',
  `parent_id` varchar(45) DEFAULT NULL COMMENT '父级操作id',
  PRIMARY KEY (`o_id`),
  UNIQUE KEY `o_id_UNIQUE` (`o_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能操作表';

-- ----------------------------
--  Table structure for `page`
-- ----------------------------
DROP TABLE IF EXISTS `page`;
CREATE TABLE `page` (
  `page_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `page_code` varchar(45) NOT NULL COMMENT '页面元素编码',
  PRIMARY KEY (`page_id`),
  UNIQUE KEY `page_id_UNIQUE` (`page_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='页面元素表';

-- ----------------------------
--  Table structure for `page_permission`
-- ----------------------------
DROP TABLE IF EXISTS `page_permission`;
CREATE TABLE `page_permission` (
  `page_id` int(11) NOT NULL COMMENT '页面主键',
  `p_id` int(11) NOT NULL COMMENT '权限主键',
  KEY `page_id_idx` (`page_id`),
  KEY `p_id_idx` (`p_id`),
  CONSTRAINT `p_id_2` FOREIGN KEY (`p_id`) REFERENCES `permission` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `page_id` FOREIGN KEY (`page_id`) REFERENCES `page` (`page_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限页面元素关联表';

-- ----------------------------
--  Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `p_type_name` varchar(45) NOT NULL COMMENT '权限类型',
  PRIMARY KEY (`p_id`),
  UNIQUE KEY `p_id_UNIQUE` (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
--  Table structure for `permission_operation`
-- ----------------------------
DROP TABLE IF EXISTS `permission_operation`;
CREATE TABLE `permission_operation` (
  `p_id` int(11) NOT NULL,
  `o_id` int(11) NOT NULL,
  PRIMARY KEY (`p_id`,`o_id`),
  KEY `fk_permission_has_operation_operation1_idx` (`o_id`),
  KEY `fk_permission_has_operation_permission1_idx` (`p_id`),
  CONSTRAINT `fk_permission_has_operation_operation1` FOREIGN KEY (`o_id`) REFERENCES `operation` (`o_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_permission_has_operation_permission1` FOREIGN KEY (`p_id`) REFERENCES `permission` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限和操作中间表';

-- ----------------------------
--  Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(45) NOT NULL COMMENT '角色名',
  PRIMARY KEY (`r_id`),
  UNIQUE KEY `r_id_UNIQUE` (`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
--  Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `p_id` int(11) NOT NULL COMMENT '角色主键',
  `r_id` int(11) NOT NULL COMMENT '权限主键',
  PRIMARY KEY (`p_id`,`r_id`),
  KEY `fk_permission_has_role_role1_idx` (`r_id`),
  KEY `fk_permission_has_role_permission1_idx` (`p_id`),
  CONSTRAINT `fk_permission_has_role_permission1` FOREIGN KEY (`p_id`) REFERENCES `permission` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_permission_has_role_role1` FOREIGN KEY (`r_id`) REFERENCES `role` (`r_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限中间表';

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(45) NOT NULL COMMENT '用户名',
  `password` varchar(45) NOT NULL COMMENT '密码',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `id_UNIQUE` (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'Leeyom', '123', '1993-08-26', '1', '24'), ('2', 'Tom', '5566', '1990-12-25', '1', '18');
COMMIT;

-- ----------------------------
--  Table structure for `user_group`
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_name` varchar(45) NOT NULL COMMENT '用户组名称',
  `parent_group_name` varchar(45) NOT NULL COMMENT '父级用户组名称',
  PRIMARY KEY (`group_id`),
  UNIQUE KEY `group_id_UNIQUE` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组';

-- ----------------------------
--  Table structure for `user_group_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_group_role`;
CREATE TABLE `user_group_role` (
  `group_id` int(11) NOT NULL COMMENT '用户组',
  `r_id` int(11) NOT NULL COMMENT '角色',
  PRIMARY KEY (`group_id`,`r_id`),
  KEY `fk_user_group_has_role_role1_idx` (`r_id`),
  KEY `fk_user_group_has_role_user_group1_idx` (`group_id`),
  CONSTRAINT `fk_user_group_has_role_role1` FOREIGN KEY (`r_id`) REFERENCES `role` (`r_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_group_has_role_user_group1` FOREIGN KEY (`group_id`) REFERENCES `user_group` (`group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组和角色中间表';

-- ----------------------------
--  Table structure for `user_group_user`
-- ----------------------------
DROP TABLE IF EXISTS `user_group_user`;
CREATE TABLE `user_group_user` (
  `group_id` int(11) NOT NULL COMMENT '用户组',
  `u_id` int(11) NOT NULL COMMENT '用户\n',
  PRIMARY KEY (`group_id`,`u_id`),
  KEY `fk_user_group_has_user_user1_idx` (`u_id`),
  KEY `fk_user_group_has_user_user_group1_idx` (`group_id`),
  CONSTRAINT `fk_user_group_has_user_user1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_group_has_user_user_group1` FOREIGN KEY (`group_id`) REFERENCES `user_group` (`group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组和用户中间表';

-- ----------------------------
--  Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `u_id` int(11) NOT NULL COMMENT '用户id',
  `r_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`u_id`,`r_id`),
  KEY `fk_user_has_role_role1_idx` (`r_id`),
  KEY `fk_user_has_role_user_idx` (`u_id`),
  CONSTRAINT `fk_user_has_role_role1` FOREIGN KEY (`r_id`) REFERENCES `role` (`r_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_role_user` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色中间表';

SET FOREIGN_KEY_CHECKS = 1;
