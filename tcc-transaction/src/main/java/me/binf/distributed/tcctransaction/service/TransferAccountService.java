package me.binf.distributed.tcctransaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferAccountService {


    @Autowired
    @Qualifier("db20JdbcTemplate")
    private JdbcTemplate db20JdbcTemplate;

    @Autowired
    @Qualifier("db21JdbcTemplate")
    private JdbcTemplate db21JdbcTemplate;

    @Transactional(transactionManager = "db20TransactionManager")
    public void transfer() {
        //银行A开始转账
        int ares = db20JdbcTemplate.update("update user_account set account=account-1000 where user_id=2");
        int res = 0;
        try {
            //第一步try 验证银行A是否转账成功
            if (ares == 0) {
                return;
            }
            // int i = 1/0;  // 运行点A发生异常
            //第二步Confirm 银行B开始转账
            res = db21JdbcTemplate.update("update user_account set account=account+1000 where user_id=2");
            //int i = 1/0;  // 运行点B发生异常
        } catch (Exception e) {
            e.printStackTrace();
            //第三步Cancel 一旦转账失败进行补偿
            //这个补偿要判断好银行B的转账是否操作成功，如果在运行点A发的异常就说明转账没有成功这个时候才需要补偿，如果是在运行点B发生的异常那么再补偿就是过度补偿
            if (res == 0) {
                //银行B没有转账成功补偿银行A
                db20JdbcTemplate.update("update user_account set account=account+1000 where user_id=2");
            }
        }
    }



}
