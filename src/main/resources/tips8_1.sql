/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : tips2

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 22/12/2019 02:27:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `article_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '//文章id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '//标题',
  `md_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '//md内容',
  `html_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '//html内容',
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '//创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '//更新时间',
  `enabled` bit(1) NOT NULL DEFAULT b'1' COMMENT '//默认为1表示通过',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '//默认为0表示未删除',
  `cover_picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '//封面图片',
  `head_picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '//顶部图片',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '//用户id',
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('123456789', 'RRRR', 'AAA', 'SSS', '2019-12-11 22:23:04', '2019-12-11 22:23:08', b'1', b'0', NULL, NULL, 'Seeumt');

-- ----------------------------
-- Table structure for article_cities
-- ----------------------------
DROP TABLE IF EXISTS `article_cities`;
CREATE TABLE `article_cities`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '途径城市表',
  `city_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '城市id',
  `article_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_cities
-- ----------------------------
INSERT INTO `article_cities` VALUES ('1', '1', '123456789');
INSERT INTO `article_cities` VALUES ('2', '2', '123456789');
INSERT INTO `article_cities` VALUES ('3', '5', '123456789');
INSERT INTO `article_cities` VALUES ('4', '6', '123456789');
INSERT INTO `article_cities` VALUES ('5', '1', '123456');

-- ----------------------------
-- Table structure for article_tags
-- ----------------------------
DROP TABLE IF EXISTS `article_tags`;
CREATE TABLE `article_tags`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '//文章与标签桥接表id',
  `tag_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '//文章对应的标签的id',
  `article_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '//文章的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_tags
-- ----------------------------
INSERT INTO `article_tags` VALUES ('1', '1', '123456789');
INSERT INTO `article_tags` VALUES ('2', '2', '123456789');
INSERT INTO `article_tags` VALUES ('3', '3', '123456789');
INSERT INTO `article_tags` VALUES ('4', '5', '123456789');

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
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city`  (
  `city_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`city_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES ('1', '北京', '2019-12-21 22:15:00', '2019-12-21 22:15:04');
INSERT INTO `city` VALUES ('2', '上海', '2019-12-21 22:15:20', '2019-12-21 22:15:23');
INSERT INTO `city` VALUES ('3', '广州', '2019-12-21 22:16:03', '2019-12-21 22:16:05');
INSERT INTO `city` VALUES ('4', '深圳', '2019-12-21 22:16:22', '2019-12-21 22:16:25');
INSERT INTO `city` VALUES ('5', '太原', '2019-12-21 22:16:36', '2019-12-21 22:16:39');
INSERT INTO `city` VALUES ('6', '天津', '2019-12-21 22:17:06', '2019-12-21 22:17:09');

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
INSERT INTO `comment` VALUES ('2da7288ce6d94cd0a731baba62117d16', 1, 'test1', '这是第一篇文章的第一条一级评论', '2019-12-21 22:01:34', '2019-12-21 22:01:34', b'1', '123456789', '123456789');
INSERT INTO `comment` VALUES ('4ab81b5ca780463e97e3f4c176db4ce5', 2, 'test2', '这是第一条二级评论', '2019-12-22 01:16:08', '2019-12-22 01:16:08', b'1', '2da7288ce6d94cd0a731baba62117d16', '123456789');
INSERT INTO `comment` VALUES ('4f318bca4e194797817efc93bef14225', 5, 'test2', '这是第二条一级评论', '2019-12-11 11:42:46', '2019-12-11 11:42:46', b'1', '227b8b1afb43408897d1dbb81eaa2ab9', '227b8b1afb43408897d1dbb81eaa2ab9');
INSERT INTO `comment` VALUES ('54ee402f47d3466ca8d3ac749b1e3c59', 6, 'test3', '这是第一条三级评论', '2019-12-11 19:33:56', '2019-12-11 19:33:56', b'1', '00b11e3531a0437490da508824e932cc', '227b8b1afb43408897d1dbb81eaa2ab9');
INSERT INTO `comment` VALUES ('6aa4ba942c2f479bb39e8c309d5b8dcb', 5, 'test1', '这是第一条一级评论', '2019-12-11 11:25:14', '2019-12-11 11:25:14', b'1', '227b8b1afb43408897d1dbb81eaa2ab9', '227b8b1afb43408897d1dbb81eaa2ab9');
INSERT INTO `comment` VALUES ('79d409dc1d1046609719cea6ba085023', 6, 'test3', '这是第二条二级评论', '2019-12-11 11:41:52', '2019-12-11 11:41:52', b'1', '6aa4ba942c2f479bb39e8c309d5b8dcb', '227b8b1afb43408897d1dbb81eaa2ab9');
INSERT INTO `comment` VALUES ('85780f68123f4a37b4d4f969fb293b6c', 6, 'test2', '这是第二条一级评论', '2019-12-11 21:48:37', '2019-12-11 21:48:37', b'1', '667b8b1afb43408897d1dbb81eaa2ab9', '667b8b1afb43408897d1dbb81eaa2ab9');
INSERT INTO `comment` VALUES ('b9234d7fc65a4c308bdc3211bcf803c2', 6, 'test3', '这是第一条四级评论', '2019-12-11 21:41:29', '2019-12-11 21:41:29', b'1', '54ee402f47d3466ca8d3ac749b1e3c59', '227b8b1afb43408897d1dbb81eaa2ab9');
INSERT INTO `comment` VALUES ('d61c3a33a5654c1a8beec5b2f6276b2d', 2, 'test3', '这是第一条三级评论', '2019-12-22 01:16:30', '2019-12-22 01:16:30', b'1', 'f2831ce2991c45b69f66e1357be10b35', '123456789');
INSERT INTO `comment` VALUES ('f2831ce2991c45b69f66e1357be10b35', 2, 'test4', '这是第二条二级评论', '2019-12-22 01:16:20', '2019-12-22 01:16:20', b'1', '2da7288ce6d94cd0a731baba62117d16', '123456789');
INSERT INTO `comment` VALUES ('f90798ae85cc49b8ac0d34b5c437284f', 1, 'test2', '这是第一篇文章的第二条一级评论', '2019-12-21 22:01:47', '2019-12-21 22:01:47', b'1', '123456789', '123456789');

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
INSERT INTO `love` VALUES ('0a92afcd8dea48339efee5b20107e333', 4, b'1', '2019-12-22 01:29:27', '2019-12-22 02:18:33', b'1', '666wx', '123456789', NULL);
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
INSERT INTO `love` VALUES ('68b2886ddce94921909e0bb774822072', 4, b'1', '2019-12-21 23:04:00', '2019-12-21 23:04:00', b'1', 'Seeumt', '123456789', NULL);
INSERT INTO `love` VALUES ('7044c48b460649cc82ff263be28b3612', 4, b'1', '2019-12-11 19:42:31', '2019-12-11 19:42:31', b'1', 'Seeumt', '00b11e3531a0437490da508824e932cc', NULL);
INSERT INTO `love` VALUES ('74b7d491ad944cbaa2418f443a786292', 4, b'1', '2019-12-11 21:18:23', '2019-12-11 21:18:23', b'1', 'Beatuiful', '227b8b1afb43408897d1dbb81eaa2ab9', NULL);
INSERT INTO `love` VALUES ('92e6df2a500b4307a35ab46ae1591694', 4, b'1', '2019-12-10 23:52:49', '2019-12-10 23:52:49', b'1', 'test4', '40214d4cb9a047f4a8d4483145636672', NULL);
INSERT INTO `love` VALUES ('99e5b3380e7147cf98d010a044bae21a', 4, b'0', '2019-12-11 20:51:45', '2019-12-11 20:51:45', b'1', 'test1', '40214d4cb9a047f4a8d4483145636672', NULL);
INSERT INTO `love` VALUES ('9efbf2795eca4de7aef779f2b88cdaad', 4, b'1', '2019-12-11 20:50:19', '2019-12-11 20:50:19', b'0', 'test2', '3bf9833b52854b25992d14e70e35c122', NULL);
INSERT INTO `love` VALUES ('a1070ff5437a45d1955f9b170b848131', 4, b'1', '2019-12-21 23:04:04', '2019-12-22 02:19:11', b'1', 'Tips', '123456789', NULL);
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
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `tag_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '//标签id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '//标签名称',
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '//创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '//更新时间',
  `enabled` bit(1) NOT NULL DEFAULT b'1' COMMENT '//是否可用',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '//是否删除',
  `admin_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '//添加此标签的管理员id',
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', '周末游', '2019-12-21 21:43:15', '2019-12-21 20:53:51', b'1', b'0', '100');
INSERT INTO `tag` VALUES ('2', '骑行', '2019-12-21 20:54:36', '2019-12-21 20:54:39', b'1', b'0', '100');
INSERT INTO `tag` VALUES ('3', '古镇', '2019-12-21 20:55:04', '2019-12-21 20:55:07', b'1', b'0', '200');
INSERT INTO `tag` VALUES ('4', '毕业游', '2019-12-21 20:55:30', '2019-12-21 20:55:33', b'1', b'0', '200');
INSERT INTO `tag` VALUES ('5', '一日游', '2019-12-21 20:56:59', '2019-12-21 20:57:02', b'1', b'0', '300');

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
-- Records of user_password
-- ----------------------------
INSERT INTO `user_password` VALUES ('1', '123', 'Seeumt');
INSERT INTO `user_password` VALUES ('2', '666', 'Tips');

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
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '//用户自定义标签id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '//标签名称',
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '//创建时间',
  `update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '//更新时间',
  `admin_id` int(32) NOT NULL COMMENT '//审核标签管理员id',
  `enabled` bit(1) NOT NULL DEFAULT b'0' COMMENT '//是否可用',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '//是否删除',
  `user_id` int(32) NOT NULL COMMENT '//所有者id',
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
