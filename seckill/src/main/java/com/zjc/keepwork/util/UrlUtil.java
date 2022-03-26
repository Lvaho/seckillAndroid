package com.zjc.keepwork.util;

public class UrlUtil {
    public final static String FAIL = "访问失败";//访问网络失败
    public final static String HEAD = "http://web.keepwork.xyz:8081";//请求头
    public final static String LOGIN_URL = HEAD+"/login/doLogin";//登录
    public final static String REGISTER_URL = HEAD+"/user/doRegister";//注册
    public final static String GET_DEPOSIT_URL =HEAD+"/deposit/getDeposit";//获取账户余额
    public final static String RECHARGE_DEPOSIT_URL = HEAD+"/deposit/doRechargemob";//充值余额
    public final static String GET_GOODSVO_URL =HEAD+"/goods/getGoods";//获取秒杀商品
    public final static String GET_SECKILL_PATH =HEAD+"/seckill/path";//获取秒杀路径
    public static String doSeckill(String path){
        return HEAD+"/seckill/"+path+"/doSeckill";
    }

}
