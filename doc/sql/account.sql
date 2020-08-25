CREATE TABLE `study`.`account`  (
  `id` int(11) NOT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `balance` float(255, 0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

INSERT INTO `study`.`account`(`id`, `user_id`, `user_name`, `balance`) VALUES (1, 1, '张三', 2000);
INSERT INTO `study`.`account`(`id`, `user_id`, `user_name`, `balance`) VALUES (2, 2, '李四', 1000);
