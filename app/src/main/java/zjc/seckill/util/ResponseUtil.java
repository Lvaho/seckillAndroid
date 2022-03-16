package zjc.seckill.util;


import com.alibaba.fastjson.JSONObject;

//用于解析tomcat返回的json字符串
public class ResponseUtil {
    public static <T>T dealresponse(String string,Class<T> c){
        return JSONObject.parseObject(string,c);
    }

}
