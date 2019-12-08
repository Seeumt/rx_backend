/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : tips

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 08/12/2019 22:12:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `md_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `html_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `love_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `comment_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NOT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'1' COMMENT '默认为1表示通过',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '默认为0表示为删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('2ce015af49804bf597e3ac3b84fe0280', '第二篇文章', '第二篇文章的mdcontent', '第二篇文章的htmlcontent', '1861d402a19f4585af580dfd01ec06a7', '4d823d06fd55489b91a420469e789b67', '2d18f1031d40406d8e5a8fc6ec276a6c', '2019-12-08 16:41:37', '2019-12-08 16:41:37', b'1', b'0');
INSERT INTO `article` VALUES ('31b3e8d4f00f4f7c8eb8b1dadb7e9226', '第一篇文章', '第一篇文章的mdcontent', '第一篇文章的htmlcontent', '3d2f192df22943c3976eba5502f8c375', 'a5951a387fb74edbaf15f75280fea14c', 'ed86278a216c4a109f44367e7095e274', '2019-12-08 14:05:04', '2019-12-08 14:05:04', b'1', b'0');
INSERT INTO `article` VALUES ('86bbff9b129d41caba1b5e6924f302da', '第三篇文章', '第三篇文章的mdcontent', '第三篇文章的htmlcontent', '74197ffd47be462fa109d143d838904c', 'cd392eb711f246a5b317a5d3b91b66bb', 'fc95177034e54b5e88e114d7d114cde5', '2019-12-08 16:58:24', '2019-12-08 16:58:24', b'1', b'0');
INSERT INTO `article` VALUES ('e2a95c4a84a24b92a28e5279cdbe27db', '第四篇文章', '第四篇文章的mdcontent', '第四篇文章的htmlcontent', '611d7eb1ae90460e8ee2100a2d319b0b', '20b9c8c1a8554ecfac6398d64248bee6', '1c58db7e0a144ce989c110ff133c66b7', '2019-12-08 17:34:44', '2019-12-08 17:34:44', b'1', b'0');

-- ----------------------------
-- Table structure for bonus
-- ----------------------------
DROP TABLE IF EXISTS `bonus`;
CREATE TABLE `bonus`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` tinyint(2) NOT NULL COMMENT '//1代表文章的评论 2代表评论的评论',
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  `enabled` bit(1) NULL DEFAULT NULL COMMENT '//1为可用 2表示上级已删除',
  `from_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `comment_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('5c8ee95f93f24fb082de715f65039b5e', 1, '2019-12-08 16:58:24', '2019-12-08 16:58:24', b'1', '8d28a34b404e47c49abc456bc83e2c2d', 'cd392eb711f246a5b317a5d3b91b66bb');
INSERT INTO `comment` VALUES ('72555300569f4317a490cbbc4aa117e0', 1, '2019-12-08 16:41:37', '2019-12-08 16:41:37', b'1', 'd46c42283e124175b3828c40dac98e1a', '4d823d06fd55489b91a420469e789b67');
INSERT INTO `comment` VALUES ('bab76ac258814172aef885347fed414e', 1, '2019-12-08 17:34:45', '2019-12-08 17:34:45', b'1', '2e72a3f14399405d8ba8822622d7f60c', '20b9c8c1a8554ecfac6398d64248bee6');

-- ----------------------------
-- Table structure for comment_from_user
-- ----------------------------
DROP TABLE IF EXISTS `comment_from_user`;
CREATE TABLE `comment_from_user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` tinyint(2) NOT NULL COMMENT '//1代表文章article 2代表动态post  3代表回复',
  `content_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `from_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `comment_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `from_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment_from_user
-- ----------------------------
INSERT INTO `comment_from_user` VALUES ('384ab50729774e0a9706034debab93f5', 1, '879d73d6009c4007988451c8eda04b4a', 'pretty', '2019-12-08 20:20:29', '2019-12-08 20:20:29', '20b9c8c1a8554ecfac6398d64248bee6', '2e72a3f14399405d8ba8822622d7f60c');
INSERT INTO `comment_from_user` VALUES ('3c8625bb5baa4c72a1e88a5e00d8410f', 1, '32a9b7b6439941a58897a461ed399898', 'Seeumt', '2019-12-08 20:21:49', '2019-12-08 20:21:49', '20b9c8c1a8554ecfac6398d64248bee6', '2e72a3f14399405d8ba8822622d7f60c');
INSERT INTO `comment_from_user` VALUES ('48a8190681414ccbbf84600ec18c4993', 1, 'bc205e7e81a1434a8f644a1d2a7f4436', 'Seeumt', '2019-12-08 20:21:43', '2019-12-08 20:21:43', '20b9c8c1a8554ecfac6398d64248bee6', '2e72a3f14399405d8ba8822622d7f60c');
INSERT INTO `comment_from_user` VALUES ('631f5d31836145a68ed5efc3e37b2fea', 1, '0991760d60f04ca4afe172defa16e7ad', 'pretty', '2019-12-08 20:20:22', '2019-12-08 20:20:22', '20b9c8c1a8554ecfac6398d64248bee6', '2e72a3f14399405d8ba8822622d7f60c');
INSERT INTO `comment_from_user` VALUES ('6ed32163fb214f92bc621b8714cd91cf', 1, '6d11e364e89a47ff84a883662e3b625d', 'Tips', '2019-12-08 20:21:27', '2019-12-08 20:21:27', '20b9c8c1a8554ecfac6398d64248bee6', '2e72a3f14399405d8ba8822622d7f60c');
INSERT INTO `comment_from_user` VALUES ('7b68e341d0a945d0ba3ff7857b82a711', 1, '1c5024222a654b81a33b5c4337ce7339', 'pretty', '2019-12-08 20:20:33', '2019-12-08 20:20:33', '20b9c8c1a8554ecfac6398d64248bee6', '2e72a3f14399405d8ba8822622d7f60c');
INSERT INTO `comment_from_user` VALUES ('b07c697ff0004f2f9d18e272805ac522', 1, 'd5b0fee38b5d4d148db9e069f759d0c0', 'Tips', '2019-12-08 20:21:31', '2019-12-08 20:21:31', '20b9c8c1a8554ecfac6398d64248bee6', '2e72a3f14399405d8ba8822622d7f60c');

-- ----------------------------
-- Table structure for content
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` tinyint(2) NOT NULL COMMENT '//1代表文章评论的内容 2代表文章评论的评论的内容 3代表动态评论的内容 4代表动态评论的回复的内容  4代表转发评论时的内容 5代表转发后评论的内容 6代表转发后评论的回复的内容',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '//0代表未删除 1代表已删除',
  `love_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `comment_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES ('32bdd5662ecb4b0c9f00afb367d627d9', '33333', 1, '2019-12-08 20:20:33', '2019-12-08 20:20:33', b'0', '0466af78bf034384a23f4d7471e25041', 'b65c7547860d4fcbb840a106abd64e57', '1c5024222a654b81a33b5c4337ce7339');
INSERT INTO `content` VALUES ('53aaf5f847c1433b997e2ba80db1a9cf', '我很开心', 1, '2019-12-08 20:21:43', '2019-12-08 20:21:43', b'0', '0c8c0901d8c44b999add084f0b306967', '9ca13a03cb144a0292e9d73aa21513c1', 'bc205e7e81a1434a8f644a1d2a7f4436');
INSERT INTO `content` VALUES ('5968c098583d4f80bc3b70a3c9523131', '11111', 1, '2019-12-08 20:20:22', '2019-12-08 20:20:22', b'0', '75fe362ce9b641a39ca743377184e474', '6547b64c50d740f3ad7d97a0dbe884b9', '0991760d60f04ca4afe172defa16e7ad');
INSERT INTO `content` VALUES ('65714f59cb61497e984a3859b02a661d', '成功了耶', 1, '2019-12-08 20:21:27', '2019-12-08 20:21:27', b'0', 'b0e9b4532a2e40b483d6c92cc1a7241e', '376a2a7d64f34a0ea556d52ab39e6d27', '6d11e364e89a47ff84a883662e3b625d');
INSERT INTO `content` VALUES ('7182463f19be45e487b020326e4c3c92', '非常开心', 1, '2019-12-08 20:21:49', '2019-12-08 20:21:49', b'0', 'e4367a0c06714a00aa7ab86dcddb7a2c', '85e4a51d4caa4e60a38c5d58c6908830', '32a9b7b6439941a58897a461ed399898');
INSERT INTO `content` VALUES ('ebd094d8b3f34c91b97c54db1f6ae936', '哈哈哈', 1, '2019-12-08 20:21:31', '2019-12-08 20:21:31', b'0', 'c5f4fabd7bb94278835c1098b8db3e27', 'a03f1209f7034cf8955241e2a0cbd3c7', 'd5b0fee38b5d4d148db9e069f759d0c0');
INSERT INTO `content` VALUES ('f0a6bd6501ab4d67b9262509c5cc5aa7', '22222', 1, '2019-12-08 20:20:29', '2019-12-08 20:20:29', b'0', '6c5c8deccae54bd1ba7e62a877495f6d', '70b4bfbfed95433ababc8f075ea5949f', '879d73d6009c4007988451c8eda04b4a');

-- ----------------------------
-- Table structure for love
-- ----------------------------
DROP TABLE IF EXISTS `love`;
CREATE TABLE `love`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` tinyint(2) NOT NULL COMMENT '//1代表文章点赞 2代表文章转发 3代表评论点赞 4代表回复点赞',
  `status` bit(1) NOT NULL COMMENT '//0代表取消赞 1代表有效赞  //0代表有效转发 1代表已删除转发',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `enabled` bit(1) NULL DEFAULT NULL COMMENT '//1代表可用 2代表上一级内容已删除',
  `from_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `love_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of love
-- ----------------------------
INSERT INTO `love` VALUES ('1536c2cf52a44907ab479f16a6fa955e', 1, b'1', '2019-12-08 16:41:37', '2019-12-08 16:41:37', b'1', 'e42228484fc94cb9974624e242a80eca', '1861d402a19f4585af580dfd01ec06a7');
INSERT INTO `love` VALUES ('36c73877dad64b12ab8605021c0dd4f1', 1, b'1', '2019-12-08 16:58:24', '2019-12-08 16:58:24', b'1', 'df7b4dd191174e488bb4f06354fbb7d9', '74197ffd47be462fa109d143d838904c');
INSERT INTO `love` VALUES ('4f36e914131144bbad5122db8e5e11e6', 2, b'1', '2019-12-08 17:34:45', '2019-12-08 17:34:45', b'1', '7d0c0cf7af694ae1bba3c5669f5563cd', '611d7eb1ae90460e8ee2100a2d319b0b');
INSERT INTO `love` VALUES ('566a12716f1e40e0b2fd88cab2364b31', 2, b'1', '2019-12-08 14:05:04', '2019-12-08 14:05:04', b'1', '8991e2b6d91b4368aa90650c417b2a5f', '3d2f192df22943c3976eba5502f8c375');
INSERT INTO `love` VALUES ('7c95551354424692b806a65becf41453', 1, b'1', '2019-12-08 14:05:04', '2019-12-08 14:05:04', b'1', 'bab5fddd72f44b6eabbe5b517f526a23', '3d2f192df22943c3976eba5502f8c375');
INSERT INTO `love` VALUES ('d497182e1f6544a58f6f9cc2a5c3fd3d', 1, b'1', '2019-12-08 17:34:45', '2019-12-08 17:34:45', b'1', 'fc4bdd6743fe4f14923f6eefcb16d751', '611d7eb1ae90460e8ee2100a2d319b0b');
INSERT INTO `love` VALUES ('d8a66c23e63148eb8ab82da2aef8496a', 2, b'1', '2019-12-08 16:58:24', '2019-12-08 16:58:24', b'1', '054026f22d024e32934be5abec9ea953', '74197ffd47be462fa109d143d838904c');
INSERT INTO `love` VALUES ('df7088e3004c455a9bc257b445f16433', 2, b'1', '2019-12-08 16:41:37', '2019-12-08 16:41:37', b'1', '75c2223bfb7e401c8e9fd3fdd0770776', '1861d402a19f4585af580dfd01ec06a7');

-- ----------------------------
-- Table structure for love_from_user
-- ----------------------------
DROP TABLE IF EXISTS `love_from_user`;
CREATE TABLE `love_from_user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `type` tinyint(2) NOT NULL COMMENT '//1代表文章点赞 2代表文章转发 3代表评论点赞 4代表回复点赞',
  `status` bit(1) NOT NULL COMMENT '//0代表赞已取消 1代表有效赞  //0代表有效转发 1代表已删除转发',
  `content_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `from_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `from_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of love_from_user
-- ----------------------------
INSERT INTO `love_from_user` VALUES ('0aadb98304044af08b99c28255b3ae2b', 1, b'1', NULL, 'Tips', '2019-12-08 21:54:18', '2019-12-08 21:54:18', 'fc4bdd6743fe4f14923f6eefcb16d751');
INSERT INTO `love_from_user` VALUES ('58104f2b43734d048adc56701188456f', 1, b'1', NULL, 'beautiful', '2019-12-08 15:41:51', '2019-12-08 15:41:51', 'bab5fddd72f44b6eabbe5b517f526a23');
INSERT INTO `love_from_user` VALUES ('755a740612e8469191407200c9dc2c2f', 1, b'1', NULL, 'Tips', '2019-12-08 14:48:46', '2019-12-08 14:48:46', 'bab5fddd72f44b6eabbe5b517f526a23');
INSERT INTO `love_from_user` VALUES ('772bbb3dfa8a4478b917844286aedefc', 1, b'1', NULL, '666wx', '2019-12-08 14:48:03', '2019-12-08 14:48:03', 'bab5fddd72f44b6eabbe5b517f526a23');
INSERT INTO `love_from_user` VALUES ('8bd8c151240c42af934f9edb718a67ca', 1, b'1', NULL, 'seeumt', '2019-12-08 14:46:14', '2019-12-08 14:46:14', 'bab5fddd72f44b6eabbe5b517f526a23');
INSERT INTO `love_from_user` VALUES ('ec0989aaba824111814a94141080cb29', 1, b'1', NULL, 'Seeumt', '2019-12-08 21:54:24', '2019-12-08 21:54:24', 'fc4bdd6743fe4f14923f6eefcb16d751');
INSERT INTO `love_from_user` VALUES ('fcb99a65f32846ed9dbf73dd27c6fc1e', 1, b'1', NULL, 'pretty', '2019-12-08 21:54:39', '2019-12-08 21:54:39', 'fc4bdd6743fe4f14923f6eefcb16d751');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` bit(1) NOT NULL COMMENT '//1代表自己原创 2代表转发他人',
  `content_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `img_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `love_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `comment_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NOT NULL,
  `deleted` bit(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for reward_point
-- ----------------------------
DROP TABLE IF EXISTS `reward_point`;
CREATE TABLE `reward_point`  (
  `id` int(32) NOT NULL,
  `quantity` int(16) NOT NULL,
  `user_id` int(32) NOT NULL,
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(32) NOT NULL,
  `type` tinyint(2) NOT NULL DEFAULT 0,
  `pid` int(32) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for souvenir_info
-- ----------------------------
DROP TABLE IF EXISTS `souvenir_info`;
CREATE TABLE `souvenir_info`  (
  `id` int(32) NOT NULL,
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `summary` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `number` int(11) NOT NULL,
  `scenic_id` int(32) NOT NULL,
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for souvenir_order
-- ----------------------------
DROP TABLE IF EXISTS `souvenir_order`;
CREATE TABLE `souvenir_order`  (
  `id` int(32) NOT NULL,
  `real_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `topic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `souvenir_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `telphone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  `status` tinyint(2) NOT NULL,
  `express` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `waybill` int(32) NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nickname` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `face_icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `telephone` int(11) NOT NULL,
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enabled` tinyint(2) NOT NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `stu` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('666wx', '666wx', '666WX', 'http://', 789, '789@163.com', 1, '2019-12-08 15:33:21', '2019-12-08 15:42:18', b'1');
INSERT INTO `user_info` VALUES ('Beautiful', 'pretty', 'cute', 'http://', 662, '662@gmail.com', 1, '2019-12-08 15:41:40', '2019-12-08 15:42:26', b'1');
INSERT INTO `user_info` VALUES ('pretty', 'fairy', 'pretty', 'http://', 666, '666@126.com', 1, '2019-12-08 21:35:22', '2019-12-08 21:35:25', b'0');
INSERT INTO `user_info` VALUES ('seeumt', 'seeumt', 'Seeumt', 'http://', 123, '456@163.com', 1, '2019-12-08 15:32:21', '2019-12-08 15:42:20', b'1');
INSERT INTO `user_info` VALUES ('Tips', 'Tips', 'tips', 'http://', 123, '123@163.com', 1, '2019-12-08 15:31:21', '2019-12-08 15:42:23', b'1');

-- ----------------------------
-- Table structure for user_password
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_stu_auth
-- ----------------------------
DROP TABLE IF EXISTS `user_stu_auth`;
CREATE TABLE `user_stu_auth`  (
  `id` int(32) NOT NULL,
  `pass` int(2) NULL DEFAULT 0,
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `admin_id` int(32) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_tag
-- ----------------------------
DROP TABLE IF EXISTS `user_tag`;
CREATE TABLE `user_tag`  (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `user_id` int(32) NOT NULL,
  `admin_id` int(32) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_third_party_info
-- ----------------------------
DROP TABLE IF EXISTS `user_third_party_info`;
CREATE TABLE `user_third_party_info`  (
  `id` int(32) NOT NULL,
  `third_party_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `third_party_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
