CREATE TABLE `study`.`schedule_detail`  (
  `schedule_detail_id` int(255) NOT NULL AUTO_INCREMENT,
	`schedule_info_id` int(255) NULL DEFAULT NULL COMMENT '还款计划表-主键',
  `batch_no` int(255) NULL DEFAULT NULL COMMENT '期数',
  `repay_int_amt` decimal(65, 0) NULL DEFAULT NULL COMMENT '还款利息',
  `repay_pri_amt` decimal(65, 0) NULL DEFAULT NULL COMMENT '还款本金',
	`repay_pay_amt` decimal(65, 0) NULL DEFAULT NULL COMMENT '还款本息',
  `sur_int_amt` decimal(65, 0) NULL DEFAULT NULL COMMENT '剩余利息',
  `sur_pri_amt` decimal(65, 0) NULL DEFAULT NULL COMMENT '剩余本金',
  `repay_date` date NULL DEFAULT NULL COMMENT '还款日期',
  `create_date` date NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`schedule_detail_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;