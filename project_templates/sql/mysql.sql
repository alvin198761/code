/*
author : ${project.author}
github: https://github.com/alvin198761/code
data: ${project.date}

*/
CREATE DATABASE ${project.name} DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use ${project.name};

SET FOREIGN_KEY_CHECKS=0;

#foreach($cls in $project.entitys)
-- ----------------------------
-- Table structure for `${cls.name}`
-- ----------------------------
DROP TABLE IF EXISTS `${cls.table_name}`;
CREATE TABLE `${cls.table_name}` (
    #foreach($field in $cls.fields)
        #if($field.name == $cls.idName)
        `${field.col_name}` ${field.sql_type} NOT NULL AUTO_INCREMENT COMMENT '${field.remark}',
        #end
         #if($field.name != $cls.idName)
        `${field.col_name}` ${field.sql_type} DEFAULT ${field.isNull} COMMENT '${field.remark}',
        #end
    #end
PRIMARY KEY (`$cls.idName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='${cls.remark}';
#end



