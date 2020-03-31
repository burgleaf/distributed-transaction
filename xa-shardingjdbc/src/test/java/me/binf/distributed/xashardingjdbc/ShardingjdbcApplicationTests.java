package me.binf.distributed.xashardingjdbc;

import me.binf.distributed.xashardingjdbc.entity.OrderInfo;
import me.binf.distributed.xashardingjdbc.service.OrderInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootTest
class ShardingjdbcApplicationTests {

    @Autowired
    OrderInfoService orderInfoService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 测试分库写数据是否生效
     */
    @Test
    void contextLoads() {
        String sql = "insert into order_info(id,order_amount,order_status,user_id) values(2,188.66,1,1)";
        int i = jdbcTemplate.update(sql);
        System.out.println("*************执行完成，影响行数:" + i);
    }

    /**
     * 测试分库查询数据是否生效
     */
    @Test
    void query() {
        String sql = "select * from order_info";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        System.out.println(mapList);
    }


    @Test
    void mybatisQuery() {
        List<OrderInfo> orderInfos = orderInfoService.findAll();
        System.out.println(orderInfos);
    }

    /**
     * 测试分布式事务
     */
    @Test
    void transactionInsert() {
        orderInfoService.insertAll();
    }

}
