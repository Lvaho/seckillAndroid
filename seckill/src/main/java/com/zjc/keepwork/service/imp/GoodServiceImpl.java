package com.zjc.keepwork.service.imp;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.xuexiang.xutil.tip.ToastUtils;
import com.zjc.keepwork.activity.MainActivity;
import com.zjc.keepwork.adapter.pojo.GoodsVo;
import com.zjc.keepwork.fragment.under_bar_fragment.MainFragment;
import com.zjc.keepwork.fragment.under_bar_fragment.SeckillFragment;
import com.zjc.keepwork.pojo.RespBean;
import com.zjc.keepwork.service.IGoodService;
import com.zjc.keepwork.util.MyApplication;
import com.zjc.keepwork.util.ResponseUtil;
import com.zjc.keepwork.util.UrlUtil;

import org.json.JSONArray;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GoodServiceImpl implements IGoodService {
    private MainActivity mainActivity;
    private MainFragment mainFragment;
    private SeckillFragment seckillFragment;
    public GoodServiceImpl(MainActivity mainActivity,MainFragment mainFragment){
        this.mainActivity=mainActivity;
        this.mainFragment=mainFragment;
    }
    public GoodServiceImpl(SeckillFragment seckillFragment){
        this.seckillFragment=seckillFragment;
    }
    private Date date =new Date();
    @Override
    public void getGoodsVo() {
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url(UrlUtil.GET_GOODSVO_URL)
                .addHeader("Cookie","userTicket="+ MyApplication.getCookie())
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
                RespBean respBean = ResponseUtil.dealresponse(response.body().string(),RespBean.class);
                if (respBean.getCode()==200 || respBean.getCode()==500704){
                List<GoodsVo> goodsVolist = ResponseUtil.dealListResponse(respBean.getObj().toString(),GoodsVo.class);
                goodsVolist.removeIf(goodsVo -> goodsVo.getEndDate().before(date));
                mainFragment.goodsVoCallBack(goodsVolist,respBean.getCode());
                }
            }
        });
    }
    @Override
    public void getAllGoodsVo() {
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url(UrlUtil.GET_GOODSVO_URL)
                .addHeader("Cookie","userTicket="+ MyApplication.getCookie())
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
                RespBean respBean = ResponseUtil.dealresponse(response.body().string(),RespBean.class);
                if (respBean.getCode()==200 || respBean.getCode()==500704){
                List<GoodsVo> goodsVolist = ResponseUtil.dealListResponse(respBean.getObj().toString(),GoodsVo.class);
                seckillFragment.goodVoCallBack(goodsVolist,respBean.getCode());
                }
            }
        });
    }
}
