package me.binf.distributed.tcctransaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransferAccountService {


    @Autowired
    @Qualifier("db20JdbcTemplate")
    private JdbcTemplate db20JdbcTemplate;

    @Autowired
    @Qualifier("db21JdbcTemplate")
    private JdbcTemplate db21JdbcTemplate;


    public void transfer() {
        db20JdbcTemplate.update("update user_account set account=account-1000 where user_id=2");
        int res = 0;
        try {
            // 运行点A发生异常
            // int i = 1/0;
            res = db21JdbcTemplate.update("update user_account set account=account+1000 where user_id=2");
            // 运行点B发生异常
             int i = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
            //这个补偿一点要判断好银行B的转账是否操作成功，如果在运行点A发的异常就说明转账没有成功这个时候才需要补偿，如果是在运行点B发生的异常那么再补偿就是过度补偿
            if (res == 0) {
                db20JdbcTemplate.update("update user_account set account=account+1000 where user_id=2");
            }
        }


    }


}
