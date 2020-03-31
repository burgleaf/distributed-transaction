package me.binf.distributed.xaatomikos.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Service
public class OrderService {



    @Transactional(transactionManager = "xaTransaction")
    public void insertTest(@Qualifier("db20") DataSource dataSource20,
                           @Qualifier("db21")DataSource dataSource21){
        JdbcTemplate jdbc195 = new JdbcTemplate(dataSource20);
        String sql1 = "INSERT INTO `order_info_1`(`id`, `order_amount`, `order_status`, `user_id`) VALUES (5, 5.00, 1, 4)";
        int i = jdbc195.update(sql1);
        System.out.println("**************影响的行数:"+i);
        int o = 1/0;
        JdbcTemplate jdbc197 = new JdbcTemplate(dataSource21);
        String sql2 = "INSERT INTO `order_info_1`(`id`, `order_amount`, `order_status`, `user_id`) VALUES (6, 6.00, 2, 4);";
        int i1 = jdbc197.update(sql2);
        System.out.println("**************影响的行数:" + i1);
    }

}
