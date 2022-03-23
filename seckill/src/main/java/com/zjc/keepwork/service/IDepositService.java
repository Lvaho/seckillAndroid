package com.zjc.keepwork.service;

import java.math.BigDecimal;

public interface IDepositService {
    public void findDepositByCookie();

    public void createOrderAndPay(BigDecimal chargenum);
}
