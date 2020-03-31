package me.binf.distributed.xashardingjdbc.service;

import me.binf.distributed.xashardingjdbc.dao.OrderInfoMapper;
import me.binf.distributed.xashardingjdbc.entity.OrderInfo;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;


    public List<OrderInfo> findAll(){
        return orderInfoMapper.findAll();
    }

    @ShardingTransactionType(TransactionType.BASE)
    @Transactional
    public void insertAll(){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(1);
        orderInfo.setOrder_amount(new BigDecimal(123));
        orderInfo.setOrder_status(1);
        orderInfo.setUser_id(1);
        orderInfoMapper.insert(orderInfo);

//        int i = 1/0;

        OrderInfo orderInfo1 = new OrderInfo();
        orderInfo1.setId(2);
        orderInfo1.setOrder_amount(new BigDecimal(222));
        orderInfo1.setOrder_status(2);
        orderInfo1.setUser_id(2);
        orderInfoMapper.insert(orderInfo1);


    }
}
