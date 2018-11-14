/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80000
Source Host           : localhost:3306
Source Database       : webosapp

Target Server Type    : MYSQL
Target Server Version : 80000
File Encoding         : 65001

Date: 2018-11-14 11:01:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin_dept`
-- ----------------------------
DROP TABLE IF EXISTS `admin_dept`;
CREATE TABLE `admin_dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(20) DEFAULT NULL COMMENT '上级ID',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `enable` tinyint(4) DEFAULT NULL COMMENT '可用标志',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin_dept
-- ----------------------------
INSERT INTO `admin_dept` VALUES ('1', '0', '总部', '1', '初始');

-- ----------------------------
-- Table structure for `admin_func`
-- ----------------------------
DROP TABLE IF EXISTS `admin_func`;
CREATE TABLE `admin_func` (
  `func_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型',
  `color` varchar(10) DEFAULT NULL COMMENT '颜色',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父结点ID',
  `tip` varchar(50) DEFAULT NULL COMMENT '提示信息',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `url` varchar(100) DEFAULT NULL COMMENT '地址',
  `order_num` smallint(5) DEFAULT NULL COMMENT '排序编号',
  PRIMARY KEY (`func_id`)
) ENGINE=InnoDB AUTO_INCREMENT=244 DEFAULT CHARSET=utf8 COMMENT='菜单';

-- ----------------------------
-- Records of admin_func
-- ----------------------------

-- ----------------------------
-- Table structure for `admin_menu`
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `icon` varchar(150) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `tip` varchar(255) DEFAULT NULL,
  `title` varchar(150) DEFAULT NULL,
  `url` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `admin_menu_rule`
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu_rule`;
CREATE TABLE `admin_menu_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `did` bigint(20) DEFAULT NULL,
  `mid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin_menu_rule
-- ----------------------------

-- ----------------------------
-- Table structure for `admin_role`
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin_role
-- ----------------------------

-- ----------------------------
-- Table structure for `admin_role_dept`
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_dept`;
CREATE TABLE `admin_role_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) DEFAULT NULL COMMENT '角色主键',
  `dept_id` int(11) DEFAULT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin_role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for `admin_role_func`
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_func`;
CREATE TABLE `admin_role_func` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) DEFAULT NULL COMMENT '角色主键',
  `user_id` int(11) DEFAULT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin_role_func
-- ----------------------------

-- ----------------------------
-- Table structure for `admin_sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `admin_sys_user`;
CREATE TABLE `admin_sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `password` varchar(100) DEFAULT NULL COMMENT '会员编号',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `department_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `wechat` varchar(50) DEFAULT NULL COMMENT '微信号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin_sys_user
-- ----------------------------
INSERT INTO `admin_sys_user` VALUES ('1004', 'admin', '21232f297a57a5a743894a0e4a801fc3', null, '1', '1', null, null, null);

-- ----------------------------
-- Table structure for `app_icon`
-- ----------------------------
DROP TABLE IF EXISTS `app_icon`;
CREATE TABLE `app_icon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint(4) DEFAULT NULL COMMENT '图标类型',
  `title` char(20) DEFAULT NULL COMMENT '显示文本',
  `tip` varchar(100) DEFAULT NULL COMMENT '悬停提示',
  `icon` varchar(200) DEFAULT NULL COMMENT '闁搞儳鍋撻悥?',
  `publish_id` bigint(20) DEFAULT NULL COMMENT '发布人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父节点',
  `route_url` varchar(200) DEFAULT NULL COMMENT '对应路由地址',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `seq_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='应用程序图标';

-- ----------------------------
-- Records of app_icon
-- ----------------------------
INSERT INTO `app_icon` VALUES ('16', '1', '网易新闻', '三石哥家的网站', 'http://192.168.1.93/webos/163.png', '0', '2018-05-26 17:03:38', '6', 'http://www.163.com', '1', '15');
INSERT INTO `app_icon` VALUES ('17', '1', '月球王子', '月球王子的github', 'http://192.168.1.93/webos/163.png', '0', '2018-05-26 17:03:42', '1', 'http://www.sina.com', '1', '16');
INSERT INTO `app_icon` VALUES ('20', '1', '酷狗音乐', 'hello kugou', 'http://pic25.nipic.com/20121121/11359010_102041377129_2.jpg', '0', '2018-05-26 17:15:08', '3', 'http://www.kugou.com/', '1', '18');
INSERT INTO `app_icon` VALUES ('21', '1', 'bilibili', '搞比利', 'http://pic25.nipic.com/20121121/11359010_102041377129_2.jpg', '0', '2018-05-26 17:27:37', '4', 'http://www.bilibili.com/', '1', '19');
INSERT INTO `app_icon` VALUES ('22', '3', '凤凰网', '一个之前喜欢的网站', 'http://192.168.1.93/webos/163.png', '0', '2018-05-26 17:28:34', '5', 'http://www.ifeng.com', '1', '20');
INSERT INTO `app_icon` VALUES ('26', '1', '测试测试', '是的发顺丰的', 'http://pic25.nipic.com/20121121/11359010_102041377129_2.jpg', '0', '2018-06-17 14:56:18', '0', 'http://www.baidu.com', '1', '1');

-- ----------------------------
-- Table structure for `base_dict`
-- ----------------------------
DROP TABLE IF EXISTS `base_dict`;
CREATE TABLE `base_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `data_key` bigint(20) DEFAULT NULL COMMENT '键',
  `data_value` varchar(50) DEFAULT NULL COMMENT '值',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `type_code` bigint(20) DEFAULT NULL COMMENT '分类',
  `type_name` varchar(50) DEFAULT NULL COMMENT '类型名称',
  `author` bigint(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of base_dict
-- ----------------------------

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint(4) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `author` bigint(20) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `c_id` bigint(20) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='捐助物资';

-- ----------------------------
-- Records of category
-- ----------------------------

-- ----------------------------
-- Table structure for `charity`
-- ----------------------------
DROP TABLE IF EXISTS `charity`;
CREATE TABLE `charity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint(4) DEFAULT NULL,
  `charity_name` varchar(200) DEFAULT NULL,
  `phone_no` char(20) DEFAULT NULL,
  `gender` char(10) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `author` bigint(20) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `p_id` bigint(20) DEFAULT NULL,
  `category` tinyint(4) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='慈善方';

-- ----------------------------
-- Records of charity
-- ----------------------------

-- ----------------------------
-- Table structure for `charity_event`
-- ----------------------------
DROP TABLE IF EXISTS `charity_event`;
CREATE TABLE `charity_event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint(4) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `chairman_id` bigint(20) DEFAULT NULL,
  `target_id` bigint(20) DEFAULT NULL,
  `event_time` datetime DEFAULT NULL,
  `author` bigint(20) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `chairmain_pnone` char(20) DEFAULT NULL,
  `target_phone` char(20) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='善行活动';

-- ----------------------------
-- Records of charity_event
-- ----------------------------
INSERT INTO `charity_event` VALUES ('1', '1', 'asdf', '123123', '1', '1', '2018-10-01 00:00:00', '1004', '123', '123', '123', '1', '2018-10-30 11:20:08');

-- ----------------------------
-- Table structure for `event_user`
-- ----------------------------
DROP TABLE IF EXISTS `event_user`;
CREATE TABLE `event_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `event_id` bigint(20) DEFAULT NULL COMMENT '活动ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '参与人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动参与方';

-- ----------------------------
-- Records of event_user
-- ----------------------------

-- ----------------------------
-- Table structure for `notes`
-- ----------------------------
DROP TABLE IF EXISTS `notes`;
CREATE TABLE `notes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(20) DEFAULT NULL COMMENT '标题',
  `content` varchar(5000) DEFAULT NULL COMMENT '内容',
  `last_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `author` bigint(20) DEFAULT NULL COMMENT '作者',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='便签信息';

-- ----------------------------
-- Records of notes
-- ----------------------------

-- ----------------------------
-- Table structure for `protocols`
-- ----------------------------
DROP TABLE IF EXISTS `protocols`;
CREATE TABLE `protocols` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `domain` varchar(100) DEFAULT NULL COMMENT 'ad域',
  `user` varchar(50) DEFAULT NULL COMMENT '用户名',
  `ip` varchar(50) DEFAULT NULL COMMENT 'ip地址',
  `port` int(11) DEFAULT NULL COMMENT '端口',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `author` bigint(20) DEFAULT NULL COMMENT '作者',
  `create_time` datetime DEFAULT NULL COMMENT '时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `type` tinyint(4) DEFAULT NULL COMMENT '协议类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='远程协议';

-- ----------------------------
-- Records of protocols
-- ----------------------------
INSERT INTO `protocols` VALUES ('3', 'test22', 'test', 'test', '3389', 'test', '0', '2018-06-09 15:40:55', '1', '1');
INSERT INTO `protocols` VALUES ('4', '111', '111', '111', '3389', '111', '0', '2018-06-09 15:42:41', '1', '1');
INSERT INTO `protocols` VALUES ('5', null, 'tetes', '213.123.123.213', '5901', 'ssss', '0', '2018-06-09 15:52:42', '1', '2');
INSERT INTO `protocols` VALUES ('7', null, '123213', '123123213', '5900', '123', '0', '2018-06-09 15:56:33', '1', '2');
INSERT INTO `protocols` VALUES ('8', null, '213213', '213231', '22', '213213', '0', '2018-06-09 16:05:32', '1', '3');
INSERT INTO `protocols` VALUES ('9', null, 'wqe', 'qwe', '22', 'qwe', '0', '2018-06-09 16:06:29', '1', '3');
INSERT INTO `protocols` VALUES ('12', null, '2323', '22323', '0', '34432', '0', '2018-06-09 16:24:22', '1', '4');
INSERT INTO `protocols` VALUES ('13', null, '123', 'werwewr', '11', '213', '0', '2018-06-09 17:47:21', '1', '4');
INSERT INTO `protocols` VALUES ('14', 'test', '123', '111122112132', '3389', '123123', '0', '2018-06-17 15:17:50', '1', '1');

-- ----------------------------
-- Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `branch_id` bigint(20) DEFAULT NULL COMMENT '门店主键',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级ID',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `enable` tinyint(4) DEFAULT NULL COMMENT '可用标志',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='部门';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------

-- ----------------------------
-- Table structure for `type_dict`
-- ----------------------------
DROP TABLE IF EXISTS `type_dict`;
CREATE TABLE `type_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='类型字典';

-- ----------------------------
-- Records of type_dict
-- ----------------------------

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `login_name` varchar(100) DEFAULT NULL COMMENT '登录名称',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `auth` tinyint(4) DEFAULT NULL COMMENT '身份',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `author` bigint(20) DEFAULT NULL COMMENT '创建人',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';

-- ----------------------------
-- Records of user_info
-- ----------------------------
