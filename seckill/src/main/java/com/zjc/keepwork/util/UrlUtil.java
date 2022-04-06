package com.zjc.keepwork.util;

public class UrlUtil {
    public final static String FAIL = "访问失败";//访问网络失败
    public final static String HEAD = "http://10.248.43.61:8080";//请求头
    public final static String LOGIN_URL = HEAD+"/login/doLogin";//登录
    public final static String REGISTER_URL = HEAD+"/user/doRegister";//注册
    public final static String GET_DEPOSIT_URL =HEAD+"/deposit/getDeposit";//获取账户余额
    public final static String RECHARGE_DEPOSIT_URL = HEAD+"/deposit/doRechargemob";//充值余额
    public final static String GET_GOODSVO_URL =HEAD+"/goods/getGoods";//获取秒杀商品
    public static String GET_SECKILL_PATH(String goodsId){
        return HEAD+"/seckill/path?goodsId="+goodsId;
    }//获取秒杀路径
    public static String doSeckill(String path){
        return HEAD+"/seckill/"+path+"/doSeckill";
    }
    public static String GET_RESULT_URL(String goodsId){
        return HEAD+"/seckill/result?goodsId="+goodsId;
    }
    public final static String PAY_ORDER_URL = HEAD+"/order/payorder";
    public static String GET_ORDER_DETAIL_URL(String orderid){return HEAD+"/order/detail?orderId="+orderid;}
    public static String GET_USER_DETAIL_URL=HEAD+"/user/getUserDetail";

}
