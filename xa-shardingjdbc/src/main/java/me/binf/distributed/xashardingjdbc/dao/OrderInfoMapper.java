package me.binf.distributed.xashardingjdbc.dao;

import me.binf.distributed.xashardingjdbc.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderInfoMapper {


    List<OrderInfo> findAll();


    int insert(OrderInfo orderInfo);
}
