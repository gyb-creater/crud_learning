-- =====================================================
-- 项目数据库初始化脚本
-- 项目名：student
-- 数据库名：mytest
-- 说明：可直接复制执行
-- =====================================================

-- 1. 创建数据库
CREATE DATABASE IF NOT EXISTS `mytest`
DEFAULT CHARACTER SET utf8
COLLATE utf8_general_ci;

USE `mytest`;

-- 2. 如果表已存在，先删除旧表
DROP TABLE IF EXISTS `user`;

-- 3. 创建 user 表
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `password` varchar(50) DEFAULT NULL COMMENT '登录密码',
  `level` varchar(10) DEFAULT NULL COMMENT '角色等级：1管理员 2教师 3学生',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- 4. 初始化测试数据
INSERT INTO `user` (`name`, `age`, `sex`, `address`, `phone`, `create_time`, `password`, `level`) VALUES
('张三', 22, '男', '上海市', '13867675656', '2020-11-18', '123456', '3'),
('李四', 20, '男', '合肥市', '13978786565', '2020-11-18', '123456', '3'),
('王五', 22, '男', '上海市', '13867675656', '2020-11-18', '123456', '3'),
('赵柳', 20, '男', '南京市', '13978786565', '2020-11-18', '123456', '3'),
('钱望', 22, '男', '深圳市', '13867675656', '2020-11-18', '123456', '3'),
('李云', 20, '男', '合肥市', '13978786565', '2020-11-18', '123456', '3'),
('张博', 22, '男', '上海市', '13867675656', '2020-11-18', '123456', '3'),
('李青', 20, '男', '合肥市', '13978786565', '2020-11-18', '123456', '3'),
('管理员', 30, '男', '北京市', '13600000000', '2020-11-18', 'admin123', '1'),
('教师A', 35, '女', '杭州市', '13700000000', '2020-11-18', 'teacher123', '2');

-- 5. 查询数据，验证是否初始化成功
SELECT * FROM `user`;

-- =====================================================
-- 测试账号
-- 管理员：用户名 管理员 / 密码 admin123 / 角色 1
-- 教师：用户名 教师A / 密码 teacher123 / 角色 2
-- 学生：用户名 张三 / 密码 123456 / 角色 3
-- =====================================================
