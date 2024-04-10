DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `username` varchar(255) NOT NULL,
  `age` varchar(255) DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
#	插入数据
INSERT INTO `t_user`(username, age) VALUES ('张三1', '18');
INSERT INTO `t_user`(username, age) VALUES ('李四1', '20');
INSERT INTO `t_user` (username, age) VALUES ('王五1', '22');