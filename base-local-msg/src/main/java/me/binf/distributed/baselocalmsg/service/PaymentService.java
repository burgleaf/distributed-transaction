package me.binf.distributed.baselocalmsg.service;

import me.binf.distributed.baselocalmsg.entity.PayMsg;
import me.binf.distributed.baselocalmsg.entity.UserAccount;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Beans;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {


    @Autowired
    @Qualifier("db20JdbcTemplate")
    private JdbcTemplate db20JdbcTemplate;


    @Transactional(transactionManager = "db20TransactionManager")
    public int payment(int uid, int order_id, int amount) {
        //查询用户账户信息
        List<Map<String,Object>> userAccountList = db20JdbcTemplate.queryForList("select id,user_id,account from user_account where user_id = ?",uid);
        if (userAccountList.size() == 0) {
            return 1;
        }

        UserAccount userAccount = new UserAccount();
        try {
            BeanUtils.populate(userAccount,userAccountList.get(0));
        }catch (Exception e){
            e.printStackTrace();
            return 1;
        }
        int account = userAccount.getAccount();
        if (account < amount) {
            return 2;
        }
        //更新用户账户金额
        userAccount.setAccount(account - amount);
        db20JdbcTemplate.update("UPDATE `user_account` SET  `account` = ? WHERE `user_id` = ?;", userAccount.getAccount(), userAccount.getUser_id());
        PayMsg payMsg = new PayMsg();
        payMsg.setId(1001);
        payMsg.setOrder_id(order_id);
        payMsg.setStatus(0);//0-未发送,1-发送成功,2-超次数
        payMsg.setFail_count(0);
        //写入本地消息表
        db20JdbcTemplate.update("INSERT INTO `pay_msg`(`id`, `order_id`, `status`, `fail_count`) VALUES (?, ?, ?, ?)", payMsg.getId(), payMsg.getOrder_id(), payMsg.getStatus(), payMsg.getFail_count());
        return 0;
    }

}
