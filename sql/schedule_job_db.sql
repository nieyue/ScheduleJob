# 数据库 
#创建数据库
DROP DATABASE IF EXISTS schedule_job_db;
CREATE DATABASE schedule_job_db;

#使用数据库 
use schedule_job_db;

#创建计划任务表
CREATE TABLE schedule_job_tb(
schedule_job_id int(11) NOT NULL AUTO_INCREMENT COMMENT '计划任务id',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
job_name varchar(255) COMMENT '任务名称',
job_group varchar(255) COMMENT '任务分组',
job_status varchar(255) COMMENT '任务状态',
cron_expression varchar(255) COMMENT 'cron表达式',
description varchar(255) COMMENT '描述 ',
job_id int(11) COMMENT '任务Id ',
type int(11) COMMENT '任务类型',
method_name varchar(255) COMMENT '任务调用的方法名',
PRIMARY KEY (schedule_job_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='计划任务表';
