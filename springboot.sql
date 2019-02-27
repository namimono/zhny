/*
 Navicat Premium Data Transfer

 Source Server         : mysql-jichao
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : springboot

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 27/02/2019 09:31:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '这个表没用' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('client', NULL, '{noop}secret', 'all', 'password,authorization_code,refresh_token,implicit,client_credentials', NULL, NULL, NULL, NULL, NULL, 'true');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '例子，角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ji');
INSERT INTO `sys_role` VALUES ('2', 'ji1');
INSERT INTO `sys_role` VALUES ('3', 'ji2');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_password_reset_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '例子，用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('02fe96615fff4383bb0fcb636cab2301', 'aaa', '$2a$10$Zw2mXx2HZGSuzvJWSyDx9ehyT7B3v2QIJouWp2yQj9XagEu2ECReK', '2018-06-27 16:09:43');
INSERT INTO `sys_user` VALUES ('10', 'i', 'i', NULL);
INSERT INTO `sys_user` VALUES ('109c5f7d3cb048028271505486bb73a6', 'jichao123', '$2a$10$ByJPnxt53o21PwuCPrMM9u66CJhdlPBkQF0V3yoKg5SiCwDxF7kvi', '2018-06-15 18:24:08');
INSERT INTO `sys_user` VALUES ('11', 'j', 'j', NULL);
INSERT INTO `sys_user` VALUES ('12', 'k', 'k', NULL);
INSERT INTO `sys_user` VALUES ('2', 'a', 'a', NULL);
INSERT INTO `sys_user` VALUES ('29dd7f22c369481bbf567bb5b787edde', 'jichao1234', '$2a$10$QreIHWRfA6cTg0rtCLvVUOEuY/AnNbbqZGYISzUAqHkDnf3eQJHM2', '2018-06-15 18:30:23');
INSERT INTO `sys_user` VALUES ('3', 'b', 'b', NULL);
INSERT INTO `sys_user` VALUES ('4', 'c', 'c', NULL);
INSERT INTO `sys_user` VALUES ('5', 'd', 'd', NULL);
INSERT INTO `sys_user` VALUES ('6', 'e', 'e', NULL);
INSERT INTO `sys_user` VALUES ('7', 'f', 'f', NULL);
INSERT INTO `sys_user` VALUES ('8', 'g', 'g', NULL);
INSERT INTO `sys_user` VALUES ('9', 'h', 'h', NULL);
INSERT INTO `sys_user` VALUES ('d23277924394441f8b135ef29d745f1a', 'jichao1', '$2a$10$zOpYacpIPr3EHJ2mfwo0aOOVIiPqVUB6bv86hkv9UxfjpTSSNAphy', '2018-06-05 00:00:00');
INSERT INTO `sys_user` VALUES ('e767a5e1d9bd4b69ba45f2bcab916f7b', 'jichao', '$2a$10$WzyB0tKzeS66wcCPExjNuenq2ot7NK2eFWOH.Fd/Q2A/HwW5HoqgS', '2018-06-05 00:00:00');

SET FOREIGN_KEY_CHECKS = 1;
