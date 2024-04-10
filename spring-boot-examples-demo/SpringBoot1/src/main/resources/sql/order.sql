DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `number` varchar(255) NOT NULL,
 `name` varchar(255) DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
#	插入数据
INSERT INTO `t_order`(number, name) VALUES ('0112', '订单1');
INSERT INTO `t_order`(number, name) VALUES ('0113', '订单2');
INSERT INTO `t_order` (number, name) VALUES ('0114', '订单3');