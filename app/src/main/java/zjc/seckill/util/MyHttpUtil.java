package zjc.seckill.util;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MyHttpUtil {
    public static String host="http://web.keepwork.xyz:8081";       //web服务器Tomcat的ip地址
    //用于向服务器发送Post请求
    public static void sendOkhttpPostRequest(String address, RequestBody requestBody, Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
    }
    //用于向服务器发送Get请求
    public static void sendOkhttpGetRequest(String address,  Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
