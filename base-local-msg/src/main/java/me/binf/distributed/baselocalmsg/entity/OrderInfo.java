package me.binf.distributed.baselocalmsg.entity;

public class OrderInfo {
    private int id;
    private int order_status;
    private int order_amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public int getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(int order_amount) {
        this.order_amount = order_amount;
    }
}
