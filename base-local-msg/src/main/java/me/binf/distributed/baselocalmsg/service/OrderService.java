package me.binf.distributed.baselocalmsg.service;

import me.binf.distributed.baselocalmsg.entity.OrderInfo;
import me.binf.distributed.baselocalmsg.entity.UserAccount;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class OrderService {



    private final JdbcTemplate db21JdbcTemplate;

    public OrderService(@Qualifier("db21JdbcTemplate") JdbcTemplate db21JdbcTemplate) {
        this.db21JdbcTemplate = db21JdbcTemplate;
    }

    /**
     *
     * @param order_id
     * @return:0-更新成功,1-订单不存在
     */
    @Transactional(transactionManager = "db21TransactionManager")
    public int handleOrder(int order_id){
        List<Map<String,Object>> orderInfoList = db21JdbcTemplate.queryForList("select id,order_status,order_amount from order_info");
        if (orderInfoList.size() == 0) {
            return 1;
        }
        OrderInfo orderInfo = new OrderInfo();
        try {
            BeanUtils.populate(orderInfo,orderInfoList.get(0));
        }catch (Exception e){
            e.printStackTrace();
            return 1;
        }
        orderInfo.setOrder_status(1);
        db21JdbcTemplate.update("update order_info set order_status =? where id = ? ",1,order_id);
        return 0;
    }
}
