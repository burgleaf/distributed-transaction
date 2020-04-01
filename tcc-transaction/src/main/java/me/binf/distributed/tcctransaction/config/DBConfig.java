package me.binf.distributed.tcctransaction.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DBConfig {



    @Primary
    @Bean("db20")
    public DataSource db20(){
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://192.168.3.20:3306/shard_order?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8")
                .username("root")
                .password("123456")
                .build();

    }

    @Bean("db21")
    public DataSource db21(){
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://192.168.3.21:3306/shard_order?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8")
                .username("root")
                .password("123456")
                .build();

    }

    @Primary
    @Bean(name="db20JdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("db20")  DataSource dataSource ) {
        return new JdbcTemplate(dataSource);
    }


    @Bean(name="db21JdbcTemplate")
    public JdbcTemplate  secondaryJdbcTemplate(@Qualifier("db21") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }



}
