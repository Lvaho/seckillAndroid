package com.zjc.keepwork.service;

import java.math.BigDecimal;

public interface IDepositService {
    public void findDepositByCookieinActivity();

    public void createOrderAndPay(BigDecimal chargenum);
    public void findDepositByCookieinFragment();
}
