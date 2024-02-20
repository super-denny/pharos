

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `avatar` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '头像',
  `account` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '账号',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `type` tinyint NOT NULL DEFAULT '1' COMMENT '类型 1-普通管理员 2-超级管理员',
  `salt` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '加密盐',
  `encrypt_times` int NOT NULL COMMENT '加密次数',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态 1-正常 2-停用',
  `delete_tag` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 1-是 0-否',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理员表';

-- ----------------------------
-- Records of admin_info
-- ----------------------------
BEGIN;
INSERT INTO `admin_info` (`id`, `name`, `avatar`, `account`, `password`, `type`, `salt`, `encrypt_times`, `status`, `delete_tag`, `gmt_create`, `gmt_modify`) VALUES (1, 'admin', 'https://cdn.webuy.ai/message/assets/img/2023/07/07/c9ad21cc-3e55-47f4-962d-c9f0434841b9__16KB____size300x254.jpeg', 'admin', 'c88137a65afcca1debd6305541f68066', 2, 'f6uvwgxw', 1, 1, 0, '2023-07-07 10:47:52', '2023-07-13 15:53:44');
COMMIT;

-- ----------------------------
-- Table structure for behavioral_statistics
-- ----------------------------
DROP TABLE IF EXISTS `behavioral_statistics`;
CREATE TABLE `behavioral_statistics` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `scene_value` int NOT NULL COMMENT '场景值',
  `scene_count` bigint NOT NULL DEFAULT '0' COMMENT '统计值',
  `attribute` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '扩展属性',
  `gmt_statistics` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '统计日期',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `index_value_stat` (`scene_value`,`gmt_statistics`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='行为统计表';

-- ----------------------------
-- Records of behavioral_statistics
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for collection_info
-- ----------------------------
DROP TABLE IF EXISTS `collection_info`;
CREATE TABLE `collection_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int NOT NULL COMMENT '用户ID',
  `url_id` int NOT NULL COMMENT '导航ID',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '0-未收藏 1-已收藏',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='收藏信息';

-- ----------------------------
-- Records of collection_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for guidance
-- ----------------------------
DROP TABLE IF EXISTS `guidance`;
CREATE TABLE `guidance` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `icon` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图标',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '地址',
  `type` tinyint NOT NULL COMMENT '类型  发布-1 审计--2 运维-3 工作台-4 仓库-5 监控-6 crm-7 其他-8',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态 1-正常 0-删除',
  `rank` int NOT NULL DEFAULT '0' COMMENT '排序',
  `delete_tag` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0-否 1-是',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='导航信息';

-- ----------------------------
-- Records of guidance
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for guidance_type
-- ----------------------------
DROP TABLE IF EXISTS `guidance_type`;
CREATE TABLE `guidance_type` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `value` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '分类值',
  `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '图标',
  `delete_tag` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0-否 1-是',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='导航分类';

-- ----------------------------
-- Records of guidance_type
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for link_info
-- ----------------------------
DROP TABLE IF EXISTS `link_info`;
CREATE TABLE `link_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `icon` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '图标',
  `url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '链接',
  `type` int NOT NULL COMMENT '导航类型',
  `user_id` int NOT NULL COMMENT '提交人',
  `privacy` tinyint NOT NULL DEFAULT '0' COMMENT '是否私有 1-是 0-否',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态 0-待审核 1-审核通过 2-审核失败',
  `fail_reason` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '失败原因',
  `admin_id` int DEFAULT NULL COMMENT '审核人ID',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of link_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `disp_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '中文名',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态1-正常 2-限制登录',
  `gmt_last_login` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户信息';

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
