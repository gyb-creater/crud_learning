-- 如果你的 user 表还是最开始那版，请先执行这段 SQL
-- 作用：补上真实注册和登录需要的 password、level 字段

ALTER TABLE `user`
ADD COLUMN `password` varchar(50) DEFAULT NULL COMMENT '登录密码' AFTER `create_time`,
ADD COLUMN `level` varchar(10) DEFAULT NULL COMMENT '角色等级：1管理员 2教师 3学生' AFTER `password`;

-- 可选：给已有数据补默认密码和角色，方便你直接测试登录
UPDATE `user`
SET `password` = '123456', `level` = '3'
WHERE `password` IS NULL OR `level` IS NULL;
