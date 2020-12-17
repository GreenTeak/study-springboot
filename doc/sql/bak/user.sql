CREATE TABLE `study`.`Untitled`  (
  `id` int(11) NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

INSERT INTO `study`.`user`(`id`, `user_name`, `age`, `password`) VALUES (1, 'L-zhangpengbo', 100, NULL);
INSERT INTO `study`.`user`(`id`, `user_name`, `age`, `password`) VALUES (2, '庞花', 33, NULL);
INSERT INTO `study`.`user`(`id`, `user_name`, `age`, `password`) VALUES (3, '张三封', 29, NULL);
INSERT INTO `study`.`user`(`id`, `user_name`, `age`, `password`) VALUES (4, '马云', 29, NULL);
INSERT INTO `study`.`user`(`id`, `user_name`, `age`, `password`) VALUES (5, '詹姆斯', 88, NULL);
INSERT INTO `study`.`user`(`id`, `user_name`, `age`, `password`) VALUES (6, '天道酬勤', 99, NULL);
INSERT INTO `study`.`user`(`id`, `user_name`, `age`, `password`) VALUES (7, '无欲则刚', 38, NULL);
INSERT INTO `study`.`user`(`id`, `user_name`, `age`, `password`) VALUES (8, 'zhangsan', 35, NULL);
INSERT INTO `study`.`user`(`id`, `user_name`, `age`, `password`) VALUES (9, '20191023测试', 98, NULL);
INSERT INTO `study`.`user`(`id`, `user_name`, `age`, `password`) VALUES (10, '测试20191101', 99, NULL);
