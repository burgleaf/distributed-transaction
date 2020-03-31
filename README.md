# 分布式事务DEMO


####XA协议两阶段提交


- xa-atomikos 项目是使用atomikos来做分布式事务

- xa-shardingjdbc 项目使用shardingjdbc在做分库测试和分布式事务


 xa协议demo使用的表**order_info_1**，其中在两台机器建了两个数据库和一张同样的表来做分布式事务实验
```sql
CREATE TABLE `order_info_1` (
  `id` int(11) NOT NULL,
  `order_amount` decimal(10,2) NOT NULL,
  `order_status` int(1) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

```