-- auto Generated on 2017-07-08 12:42:37 
-- DROP TABLE IF EXISTS `CATEGORY`; 
CREATE TABLE `CATEGORY`(
    `NAME` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'name',
    `SORT` INT (11) NOT NULL DEFAULT 0 COMMENT 'sort',
    `ID` INT (11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `CREATE_TIME` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'createTime',
    INDEX(ID),
    PRIMARY KEY (`ID`)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '`CATEGORY`';