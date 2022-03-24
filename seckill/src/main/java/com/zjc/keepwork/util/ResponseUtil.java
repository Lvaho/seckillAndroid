package com.zjc.keepwork.util;


import com.alibaba.fastjson.JSONObject;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

//用于解析tomcat返回数据
public class ResponseUtil {
    public static <T>T dealresponse(String string,Class<T> c){
        return JSONObject.parseObject(string,c);
    }
    public static <T>List<T> dealListResponse(String string,Class<T> c){
        return JSONObject.parseArray(string,c);
    }

}
