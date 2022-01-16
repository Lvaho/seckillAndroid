package zjc.seckill.service.imp;


import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import zjc.seckill.activity.LoginActivity;
import zjc.seckill.pojo.ResponseBody;
import zjc.seckill.service.LoginService;
import zjc.seckill.util.MD5Util;
import zjc.seckill.util.MyHttpUtil;

public class LoginServiceImp implements LoginService {
    private LoginActivity loginActivity;
    private ResponseBody responseBody=new ResponseBody();
    public LoginServiceImp(LoginActivity loginActivity){this.loginActivity=loginActivity;}

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
    public void doLogin(String mobile, String password) {
        MD5Util md5Util=new MD5Util();
        String salt="1a2b3c4d";
        String str = "" + salt.charAt(0) + salt.charAt(2) + password + salt.charAt(5) + salt.charAt(4);
        password= md5Util.md5(str);
    //构造LoginValidate的tomcat服务请求URL
        String loginValidateURL= MyHttpUtil.host+"/login/doLogin";
        loginValidateURL+="?mobile="+mobile+"&password="+password;
        Log.i("zjc",loginValidateURL);
        MyHttpUtil.sendOkhttpGetRequest(loginValidateURL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("zjc",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                parseJSONtoUser(response.body().string());
                if(responseBody.getResponseCode().equals("200")){
                    loginActivity.loginCallback(responseBody);
                }else {
                    Log.i("zjc",responseBody.getResponseCode());
                }
                loginActivity.printErrorMessage(responseBody.getResponseMessage());

            }

        });
    }
}
