package com.zjc.keepwork.service.imp;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.xuexiang.xutil.tip.ToastUtils;
import com.zjc.keepwork.activity.MainActivity;
import com.zjc.keepwork.adapter.pojo.GoodsVo;
import com.zjc.keepwork.fragment.under_bar_fragment.MainFragment;
import com.zjc.keepwork.pojo.RespBean;
import com.zjc.keepwork.service.IGoodService;
import com.zjc.keepwork.util.ResponseUtil;
import com.zjc.keepwork.util.UrlUtil;

import org.json.JSONArray;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GoodServiceImpl implements IGoodService {
    private MainActivity mainActivity;
    private MainFragment mainFragment;
    public GoodServiceImpl(MainActivity mainActivity,MainFragment mainFragment){
        this.mainActivity=mainActivity;
        this.mainFragment=mainFragment;
    }

    @Override
    public void getGoodsVo() {
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url(UrlUtil.GET_GOODSVO_URL)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("zjc",e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("zjc","请求成功");
                List<GoodsVo> goodsVolist = ResponseUtil.dealListResponse(response.body().string(),GoodsVo.class);
                ToastUtils.toast(goodsVolist.toString());
                mainFragment.goodsVoCallBack(goodsVolist);
            }
        });
    }
}
