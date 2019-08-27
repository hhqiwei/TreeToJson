-- 生成数据表 【使用navicat premium 批量生成测试数据】（https://blog.csdn.net/yxw_android/article/details/80817174）
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `pid` bigint(20) NULL NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='市级信息';

SET FOREIGN_KEY_CHECKS = 1;




-- 自动生成数据
DROP PROCEDURE IF EXISTS proc_initData;--如果存在此存储过程则删掉
DELIMITER $
CREATE PROCEDURE proc_initData()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i<=10000 DO
        INSERT

    INTO city(name,pid) VALUES(concat('ales',i),FLOOR(RAND() * 100));
        SET i = i+1;
    END WHILE;
END $
CALL proc_initData();