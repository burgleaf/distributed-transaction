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

### 本地消息表实现最终一致性

#### base-local-msg 项目使用的表和数据

需要注意的是在这个demo里，我把支付业务和订单业务放到一个服务里了。实际上应该是两个不同的服务，这样才需要解决分布式事务问题。
假如你的项目订单业务和支付业务是在一个服务里，就直接用本地事务来解决就可以了。
- 支付数据库
```sql
# 用户账户信息表
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `account` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

INSERT INTO `shard_order`.`user_account`(`id`, `user_id`, `account`) VALUES (1, 2, 990.00);

# 支付消息表
CREATE TABLE `pay_msg` (
  `id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `fail_count` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

- 订单数据库
```sql
# 订单信息表
CREATE TABLE `order_info` (
  `id` int(11) NOT NULL,
  `order_status` int(11) NOT NULL,
  `order_amount` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `shard_order`.`order_info`(`id`, `order_status`, `order_amount`) VALUES (1001, 1, 10);

```