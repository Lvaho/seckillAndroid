package com.zjc.keepwork.pojo;

import java.util.Date;

public class GoodsDetailVo {
    private String goodsName;
    private String goodsStatus;
    private String goodsPrice;
    private Date createtime;
    public String getGoodsName() {
        return goodsName;
    }
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    public String getGoodsStatus() {
        return goodsStatus;
    }
    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }
    public String getGoodsPrice() {
        return goodsPrice;
    }
    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
