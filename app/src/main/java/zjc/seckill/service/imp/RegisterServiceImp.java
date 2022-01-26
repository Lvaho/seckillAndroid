package zjc.seckill.service.imp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import zjc.seckill.activity.RegisterActivity;
import zjc.seckill.pojo.ResponseBody;
import zjc.seckill.service.RegisterService;
import zjc.seckill.util.MD5Util;
import zjc.seckill.util.MyHttpUtil;

public class RegisterServiceImp implements RegisterService {
    private RegisterActivity registerActivity;
    private ResponseBody responseBody=new ResponseBody();
    public RegisterServiceImp(RegisterActivity registerActivity){
        this.registerActivity=registerActivity;
    }
    //用于解析tomcat返回的json字符串
    private void parseJSONtoUser(String responseData){
        try {
            JSONObject jsonObject=new JSONObject(responseData);
            String code=jsonObject.getString("code");
            responseBody.setResponseCode(code);
            String message=jsonObject.getString("message");
            responseBody.setResponseMessage(message);
            String obj=jsonObject.getString("obj");
            responseBody.setResponseObj(obj);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doRegister(String identity, String mobile, String nickname, String password) {
        MD5Util md5Util=new MD5Util();
        String salt="1a2b3c4d";
        String str = "" + salt.charAt(0) + salt.charAt(2) + password + salt.charAt(5) + salt.charAt(4);
        password= md5Util.md5(str);
        String registerValidateURL= MyHttpUtil.host+"/user/doRegister";
        registerValidateURL+="?identity="+identity+"&mobile="+mobile+"&nickname="+nickname+"&password="+password;
        Log.i("zjc",registerValidateURL);
        MyHttpUtil.sendOkhttpGetRequest(registerValidateURL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("zjc",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                parseJSONtoUser(response.body().string());
                if(responseBody.getResponseCode().equals("200")){
                    registerActivity.registerCallback();
                    Log.i("zjc",responseBody.getResponseCode());
                }
                registerActivity.printResponseMessage(responseBody.getResponseMessage());
            }
        });
    }
}
