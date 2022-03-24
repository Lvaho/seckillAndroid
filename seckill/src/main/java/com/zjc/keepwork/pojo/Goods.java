package com.zjc.keepwork.pojo;

import java.math.BigDecimal;

public class Goods {
    private Integer id;

    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品标题
     */
    private String goodsTitle;

    /**
     * 商品图片
     */
    private String goodsImg;

    /**
     * 商品详情
     */
    private String goodsDetail;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 商品库存
     */
    private Integer goodsStock;

}
