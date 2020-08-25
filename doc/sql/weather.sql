## jpa已经创建了表

CREATE TABLE `study`.`weather`  (
  `weather_id` bigint(20) NOT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `ganmao` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wendu` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`weather_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;