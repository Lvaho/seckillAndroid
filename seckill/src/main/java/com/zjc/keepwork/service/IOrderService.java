package com.zjc.keepwork.service;

public interface IOrderService {
    void payOrder(String orderid);

    void getOrderByOrderId(String orderid);
}
