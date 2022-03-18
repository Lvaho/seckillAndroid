package com.zjc.keepwork.util;


import com.alibaba.fastjson.JSONObject;

//用于解析tomcat返回数据
public class ResponseUtil {
    public static <T>T dealresponse(String string,Class<T> c){
        return JSONObject.parseObject(string,c);
    }

}
