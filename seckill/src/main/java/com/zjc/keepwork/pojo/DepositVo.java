package com.zjc.keepwork.pojo;

import java.math.BigDecimal;

public class DepositVo {
    private int id;
    private String identity;
    private BigDecimal deposit;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getIdentity() {
        return identity;
    }
    public void setIdentity(String identity) {
        this.identity = identity;
    }
    public BigDecimal getDeposit() {
        return deposit;
    }
    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    @Override
    public String toString() {
        return "DepositVo{" +
                "id=" + id +
                ", identity='" + identity + '\'' +
                ", deposit=" + deposit +
                '}';
    }
}
