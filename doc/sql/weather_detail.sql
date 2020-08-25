## jpa已经创建了表，但是由于天气预报的数据量太大，导致联查的时候等待时间太长，所以给weather_detail_id加索引

## 增加索引
	alter table weather_detail add index index_weather_id (weather_id);

## 或者直接执行建表语句也可以
CREATE TABLE `study`.`weather_detail`  (
  `weather_detail_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fengli` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fengxiang` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `high` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `low` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `weather_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`weather_detail_id`) USING BTREE,
  INDEX `index_weather_id`(`weather_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 18697 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;