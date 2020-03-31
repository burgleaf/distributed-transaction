package me.binf.distributed.xashardingjdbc.entity;

import java.math.BigDecimal;

public class OrderInfo {

    private Integer id;

    private BigDecimal order_amount;

    private Integer order_status;

    private Integer user_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(BigDecimal order_amount) {
        this.order_amount = order_amount;
    }

    public Integer getOrder_status() {
        return order_status;
    }

    public void setOrder_status(Integer order_status) {
        this.order_status = order_status;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
