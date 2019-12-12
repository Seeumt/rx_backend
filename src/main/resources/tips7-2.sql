/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : tips2

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 11/12/2019 22:35:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `article_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `md_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `html_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `owner_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NOT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'1' COMMENT '默认为1表示通过',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '默认为0表示为删除',
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('123456789', 'RRRR', 'AAA', 'SSS', 'Seeumt', '2019-12-11 22:23:04', '2019-12-11 22:23:08', b'1', b'0');

-- ----------------------------
-- Table structure for bonus
-- ----------------------------
DROP TABLE IF EXISTS `bonus`;
CREATE TABLE `bonus`  (
  `bonus_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`bonus_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `comment_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `type` tinyint(2) NOT NULL COMMENT '//1代表文章的评论 2代表文章评论的评论 3代表动态的评论 4代表动态评论的评论\r\n',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `content` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  `enabled` bit(1) NOT NULL COMMENT '//1为可用 2表示上级已删除',
  `parent_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '//0级评论指的就是文章、动态本身 其默认设置为',
  `api_root_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '//article_id   post_id   comment_id',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('00b11e3531a0437490da508824e932cc', 6, 'test2', '这是第一条二级评论', '2019-12-11 11:40:00', '2019-12-11 11:40:00', b'1', '6aa4ba942c2f479bb39e8c309d5b8dcb', '227b8b1afb43408897d1dbb81eaa2ab9');
INSERT INTO `comment` VALUES ('023b5d2a91a940ada79f4f5c0be9e231', 6, 'test1', '这是第一条一级评论', '2019-12-11 21:48:21', '2019-12-11 21:48:21', b'1', '667b8b1afb43408897d1dbb81eaa2ab9', '667b8b1afb43408897d1dbb81eaa2ab9');
INSERT INTO `comment` VALUES ('0cc8af26505d41fab32f097623c5d805', 6, 'test2', '这是第三条二级评论', '2019-12-11 21:44:25', '2019-12-11 21:44:25', b'1', '4f318bca4e194797817efc93bef14225', '227b8b1afb43408897d1dbb81eaa2ab9');
INSERT INTO `comment` VALUES ('4f318bca4e194797817efc93bef14225', 5, 'test2', '这是第二条一级评论', '2019-12-11 11:42:46', '2019-12-11 11:42:46', b'1', '227b8b1afb43408897d1dbb81eaa2ab9', '227b8b1afb43408897d1dbb81eaa2ab9');
INSERT INTO `comment` VALUES ('54ee402f47d3466ca8d3ac749b1e3c59', 6, 'test3', '这是第一条三级评论', '2019-12-11 19:33:56', '2019-12-11 19:33:56', b'1', '00b11e3531a0437490da508824e932cc', '227b8b1afb43408897d1dbb81eaa2ab9');
INSERT INTO `comment` VALUES ('6aa4ba942c2f479bb39e8c309d5b8dcb', 5, 'test1', '这是第一条一级评论', '2019-12-11 11:25:14', '2019-12-11 11:25:14', b'1', '227b8b1afb43408897d1dbb81eaa2ab9', '227b8b1afb43408897d1dbb81eaa2ab9');
INSERT INTO `comment` VALUES ('79d409dc1d1046609719cea6ba085023', 6, 'test3', '这是第二条二级评论', '2019-12-11 11:41:52', '2019-12-11 11:41:52', b'1', '6aa4ba942c2f479bb39e8c309d5b8dcb', '227b8b1afb43408897d1dbb81eaa2ab9');
INSERT INTO `comment` VALUES ('85780f68123f4a37b4d4f969fb293b6c', 6, 'test2', '这是第二条一级评论', '2019-12-11 21:48:37', '2019-12-11 21:48:37', b'1', '667b8b1afb43408897d1dbb81eaa2ab9', '667b8b1afb43408897d1dbb81eaa2ab9');
INSERT INTO `comment` VALUES ('b9234d7fc65a4c308bdc3211bcf803c2', 6, 'test3', '这是第一条四级评论', '2019-12-11 21:41:29', '2019-12-11 21:41:29', b'1', '54ee402f47d3466ca8d3ac749b1e3c59', '227b8b1afb43408897d1dbb81eaa2ab9');

-- ----------------------------
-- Table structure for love
-- ----------------------------
DROP TABLE IF EXISTS `love`;
CREATE TABLE `love`  (
  `love_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` tinyint(2) NOT NULL COMMENT '//1代表文章点赞 2代表文章转发 3代表评论点赞 4代表回复点赞',
  `status` bit(1) NOT NULL COMMENT '//0代表取消赞 1代表有效赞  //0代表有效转发 1代表已删除转发',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `enabled` bit(1) NOT NULL COMMENT '//1代表可用 2代表上一级内容已删除',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `api_root_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`love_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of love
-- ----------------------------
INSERT INTO `love` VALUES ('11a6648145414ceb8dc4f1e78c2c8a5c', 4, b'0', '2019-12-11 20:54:29', '2019-12-11 20:54:29', b'1', 'Beatuiful', '4f318bca4e194797817efc93bef14225', NULL);
INSERT INTO `love` VALUES ('176d0181c9c94ed9b9c105b37a223118', 4, b'1', '2019-12-10 23:10:01', '2019-12-10 23:10:01', b'1', 'Seeumt', '227b8b1afb43408897d1dbb81eaa2ab9', NULL);
INSERT INTO `love` VALUES ('1777ffa6c3e3418586ce5d3c2bad5300', 4, b'1', '2019-12-11 20:02:47', '2019-12-11 20:02:47', b'1', 'Tips', '6aa4ba942c2f479bb39e8c309d5b8dcb', NULL);
INSERT INTO `love` VALUES ('2e20865240aa455cb13394a977a046d0', 4, b'1', '2019-12-11 20:50:15', '2019-12-11 20:50:15', b'0', 'test1', '40d50cb4287f47359ee58451d923fe22', NULL);
INSERT INTO `love` VALUES ('2e3999ab83cb4327806fcd02e75d6430', 4, b'1', '2019-12-10 23:10:04', '2019-12-10 23:10:04', b'1', 'Tips', '227b8b1afb43408897d1dbb81eaa2ab9', NULL);
INSERT INTO `love` VALUES ('30f3cf5d71f54caeb94810baee320359', 4, b'0', '2019-12-11 20:51:49', '2019-12-11 20:51:49', b'1', 'Beautiful', '227b8b1afb43408897d1dbb81eaa2ab9', NULL);
INSERT INTO `love` VALUES ('344f6bd091154496981e9c092446c808', 4, b'1', '2019-12-10 23:52:39', '2019-12-10 23:52:39', b'1', 'test2', '40214d4cb9a047f4a8d4483145636672', NULL);
INSERT INTO `love` VALUES ('3c5219a8e5024a0a9013cc42ae971ca7', 4, b'1', '2019-12-11 20:50:16', '2019-12-11 20:50:16', b'0', 'test3', '40d50cb4287f47359ee58451d923fe22', NULL);
INSERT INTO `love` VALUES ('557e6fbd89554203be531bb1093d5355', 4, b'0', '2019-12-11 20:51:43', '2019-12-11 20:51:43', b'0', 'test3', '3bf9833b52854b25992d14e70e35c122', NULL);
INSERT INTO `love` VALUES ('5b24c891cc65411eaf24a2b086cb0c76', 4, b'1', '2019-12-11 20:02:39', '2019-12-11 20:02:39', b'1', 'Seeumt', '6aa4ba942c2f479bb39e8c309d5b8dcb', NULL);
INSERT INTO `love` VALUES ('7044c48b460649cc82ff263be28b3612', 4, b'1', '2019-12-11 19:42:31', '2019-12-11 19:42:31', b'1', 'Seeumt', '00b11e3531a0437490da508824e932cc', NULL);
INSERT INTO `love` VALUES ('74b7d491ad944cbaa2418f443a786292', 4, b'1', '2019-12-11 21:18:23', '2019-12-11 21:18:23', b'1', 'Beatuiful', '227b8b1afb43408897d1dbb81eaa2ab9', NULL);
INSERT INTO `love` VALUES ('92e6df2a500b4307a35ab46ae1591694', 4, b'1', '2019-12-10 23:52:49', '2019-12-10 23:52:49', b'1', 'test4', '40214d4cb9a047f4a8d4483145636672', NULL);
INSERT INTO `love` VALUES ('99e5b3380e7147cf98d010a044bae21a', 4, b'0', '2019-12-11 20:51:45', '2019-12-11 20:51:45', b'1', 'test1', '40214d4cb9a047f4a8d4483145636672', NULL);
INSERT INTO `love` VALUES ('9efbf2795eca4de7aef779f2b88cdaad', 4, b'1', '2019-12-11 20:50:19', '2019-12-11 20:50:19', b'0', 'test2', '3bf9833b52854b25992d14e70e35c122', NULL);
INSERT INTO `love` VALUES ('a6a40b388add476bb5464c981560ffa4', 4, b'1', '2019-12-11 21:46:21', '2019-12-11 21:46:21', b'1', 'Tips', '667b8b1afb43408897d1dbb81eaa2ab9', NULL);
INSERT INTO `love` VALUES ('a845827777204975883813bae5e259cb', 4, b'0', '2019-12-11 20:51:46', '2019-12-11 20:51:46', b'0', 'test1', '3bf9833b52854b25992d14e70e35c122', NULL);
INSERT INTO `love` VALUES ('a9b52c07f00a42f5b363019074d6365d', 4, b'1', '2019-12-11 00:04:55', '2019-12-11 00:04:55', b'1', 'test4', '79c29537d66d4e9f919ec41b5c044bde', NULL);
INSERT INTO `love` VALUES ('b814fa8bcdce4a758af5a4164cdb6adb', 4, b'1', '2019-12-11 20:02:24', '2019-12-11 20:02:24', b'1', 'Seeumt', '4f318bca4e194797817efc93bef14225', NULL);
INSERT INTO `love` VALUES ('c884c99159364704ae99127b514e5432', 4, b'1', '2019-12-11 20:50:24', '2019-12-11 20:50:24', b'0', 'test2', '40d50cb4287f47359ee58451d923fe22', NULL);
INSERT INTO `love` VALUES ('cb64639ffc844c07a8bb58eda24e6ae0', 4, b'1', '2019-12-11 20:01:50', '2019-12-11 20:01:50', b'1', 'Tips', '4f318bca4e194797817efc93bef14225', NULL);
INSERT INTO `love` VALUES ('e3635b78b5434ec8aab7ad64891573c0', 4, b'1', '2019-12-10 23:52:43', '2019-12-10 23:52:43', b'1', 'test3', '40214d4cb9a047f4a8d4483145636672', NULL);
INSERT INTO `love` VALUES ('f8a1c97bc3bb48aeaf7e86941dac3912', 4, b'1', '2019-12-11 19:43:35', '2019-12-11 19:43:35', b'1', 'Tips', '00b11e3531a0437490da508824e932cc', NULL);
INSERT INTO `love` VALUES ('fbfd983a8b5049808ddca97c239ed10e', 4, b'1', '2019-12-11 20:03:05', '2019-12-11 20:03:05', b'1', 'Beatuiful', '6aa4ba942c2f479bb39e8c309d5b8dcb', NULL);

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `post_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` bit(1) NULL DEFAULT NULL COMMENT '//1代表自己原创 2代表转发他人',
  `content` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `img_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NOT NULL,
  `deleted` bit(1) NOT NULL,
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('227b8b1afb43408897d1dbb81eaa2ab9', b'1', 'post....', '3', '4', '2019-12-11 20:31:57', '2019-12-10 22:45:28', b'0');
INSERT INTO `post` VALUES ('667b8b1afb43408897d1dbb81eaa2ab9', b'1', '又一个post....', '3', '4', '2019-12-11 20:31:57', '2019-12-10 22:45:28', b'0');

-- ----------------------------
-- Table structure for post_comment
-- ----------------------------
DROP TABLE IF EXISTS `post_comment`;
CREATE TABLE `post_comment`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` tinyint(2) NOT NULL COMMENT '//1代表文章的评论 2代表评论的评论',
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  `enabled` bit(1) NULL DEFAULT NULL COMMENT '//1为可用 2表示上级已删除',
  `reply_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '//跟其上级评论的id对应',
  `post_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post_comment
-- ----------------------------
INSERT INTO `post_comment` VALUES ('27ae4c32ce3049d888e5f507250358d1', 6, '2019-12-10 18:31:07', '2019-12-10 18:31:07', b'0', '40214d4cb9a047f4a8d4483145636672', '227b8b1afb43408897d1dbb81eaa2ab9', 'HHHH');
INSERT INTO `post_comment` VALUES ('3b5a37fd134e49fd915b9ce84aef9a26', 6, '2019-12-10 18:16:59', '2019-12-10 18:16:59', b'0', 'a9bbe61db6ff4ad0a51695b78c432318', '227b8b1afb43408897d1dbb81eaa2ab9', 'test5');
INSERT INTO `post_comment` VALUES ('3bf9833b52854b25992d14e70e35c122', 6, '2019-12-10 18:11:47', '2019-12-10 18:11:47', b'0', 'bd5ca91ebd714b5c8b43b153f948d0b3', '227b8b1afb43408897d1dbb81eaa2ab9', 'test2');
INSERT INTO `post_comment` VALUES ('3f6db8ba7dfc4ff8a342ad0b637004a5', 6, '2019-12-10 10:05:36', '2019-12-10 10:05:36', b'0', 'bd5ca91ebd714b5c8b43b153f948d0b3', '227b8b1afb43408897d1dbb81eaa2ab9', 'Tips');
INSERT INTO `post_comment` VALUES ('40214d4cb9a047f4a8d4483145636672', 5, '2019-12-10 18:20:47', '2019-12-10 18:20:47', b'0', '227b8b1afb43408897d1dbb81eaa2ab9', '227b8b1afb43408897d1dbb81eaa2ab9', 'RRRR');
INSERT INTO `post_comment` VALUES ('40d50cb4287f47359ee58451d923fe22', 6, '2019-12-10 18:31:47', '2019-12-10 18:31:47', b'0', '27ae4c32ce3049d888e5f507250358d1', '227b8b1afb43408897d1dbb81eaa2ab9', 'AAAA');
INSERT INTO `post_comment` VALUES ('79c29537d66d4e9f919ec41b5c044bde', 6, '2019-12-10 18:12:59', '2019-12-10 18:12:59', b'0', 'bd5ca91ebd714b5c8b43b153f948d0b3', '227b8b1afb43408897d1dbb81eaa2ab9', 'test3');
INSERT INTO `post_comment` VALUES ('947f297885934839b8d3695c656f345f', 5, '2019-12-10 10:08:42', '2019-12-10 10:08:42', b'0', '227b8b1afb43408897d1dbb81eaa2ab9', '227b8b1afb43408897d1dbb81eaa2ab9', 'seeumt');
INSERT INTO `post_comment` VALUES ('a0206fd03fc74ddbbc7e8379ed3b1fcf', 6, '2019-12-10 18:17:23', '2019-12-10 18:17:23', b'0', 'a9bbe61db6ff4ad0a51695b78c432318', '227b8b1afb43408897d1dbb81eaa2ab9', 'test6');
INSERT INTO `post_comment` VALUES ('a9bbe61db6ff4ad0a51695b78c432318', 6, '2019-12-10 18:16:37', '2019-12-10 18:16:37', b'0', '947f297885934839b8d3695c656f345f', '227b8b1afb43408897d1dbb81eaa2ab9', 'test4');
INSERT INTO `post_comment` VALUES ('bd5ca91ebd714b5c8b43b153f948d0b3', 5, '2019-12-10 09:35:45', '2019-12-10 09:35:45', b'0', '227b8b1afb43408897d1dbb81eaa2ab9', '227b8b1afb43408897d1dbb81eaa2ab9', 'Seeumt');
INSERT INTO `post_comment` VALUES ('c74e0c1735d94bcb9f6f080f408f1eea', 5, '2019-12-10 20:42:48', '2019-12-10 20:42:48', b'0', '227b8b1afb43408897d1dbb81eaa2ab9', '227b8b1afb43408897d1dbb81eaa2ab9', 'Tips');
INSERT INTO `post_comment` VALUES ('cff2548f4fc74f398c617c5cea8cc5a5', 6, '2019-12-10 10:09:23', '2019-12-10 10:09:23', b'0', 'bd5ca91ebd714b5c8b43b153f948d0b3', '227b8b1afb43408897d1dbb81eaa2ab9', 'test1');
INSERT INTO `post_comment` VALUES ('dab28cbed9234bc4a89e980b6319ad6c', 6, '2019-12-10 10:06:35', '2019-12-10 10:06:35', b'0', 'bd5ca91ebd714b5c8b43b153f948d0b3', '227b8b1afb43408897d1dbb81eaa2ab9', 'Beautiful');

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
  `status` bit(1) NOT NULL DEFAULT b'0',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `stu` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('666wx', '666wx', '666WX', 'http://', 789, '789@163.com', b'1', '2019-12-08 15:33:21', '2019-12-08 15:42:18', b'1');
INSERT INTO `user_info` VALUES ('Beatuiful', 'miss', 'miss', 'http://miss', 123, '1563@qq.com', b'1', '2019-12-11 20:04:29', '2019-12-11 20:04:32', b'0');
INSERT INTO `user_info` VALUES ('Beautiful', 'pretty', 'cute', 'http://', 662, '662@gmail.com', b'1', '2019-12-08 15:41:40', '2019-12-08 15:42:26', b'1');
INSERT INTO `user_info` VALUES ('pretty', 'fairy', 'pretty', 'http://', 666, '666@126.com', b'1', '2019-12-08 21:35:22', '2019-12-08 21:35:25', b'0');
INSERT INTO `user_info` VALUES ('Seeumt', 'seeumt', 'Seeumt', 'http://', 123, '456@163.com', b'1', '2019-12-08 15:32:21', '2019-12-09 09:46:56', b'1');
INSERT INTO `user_info` VALUES ('test1', 'test1', 'test001', 'http://test1', 100, '111', b'1', '2019-12-09 20:00:40', '2019-12-10 23:46:53', b'1');
INSERT INTO `user_info` VALUES ('test2', 'test2', 'test002', 'http://test2', 200, '222', b'1', '2019-12-09 20:01:16', '2019-12-10 23:46:56', b'1');
INSERT INTO `user_info` VALUES ('test3', 'test3', 'test003', 'http://test3', 300, '333', b'1', '2019-12-09 20:01:49', '2019-12-10 23:46:59', b'1');
INSERT INTO `user_info` VALUES ('test4', 'test4', 'test004', 'http://test4', 400, '444', b'1', '2019-12-09 20:02:49', '2019-12-10 23:47:04', b'1');
INSERT INTO `user_info` VALUES ('Tips', 'Tips', 'tips', 'http://', 123, '123@163.com', b'1', '2019-12-08 15:31:21', '2019-12-08 15:42:23', b'1');

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
