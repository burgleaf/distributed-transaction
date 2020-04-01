# 分布式事务DEMO


分布式事务理解和实践的文章可以看这里https://www.jianshu.com/p/4e3f45ed36fa

### XA协议两阶段提交


- xa-atomikos 项目是使用atomikos做分布式事务

- xa-shardingjdbc 项目使用shardingjdbc和mybatis做分库测试和分布式事务


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

### TCC事务补偿机制

- tcc-transaction 项目使用的表和数据

```sql
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `account` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

//数据库A数据
INSERT INTO `shard_order`.`user_account`(`id`, `user_id`, `account`) VALUES (1, 2, 2000.00);

//数据库B数据
INSERT INTO `shard_order`.`user_account`(`id`, `user_id`, `account`) VALUES (1, 2, 1000.00);

```