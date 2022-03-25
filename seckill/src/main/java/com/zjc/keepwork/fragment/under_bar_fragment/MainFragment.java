package com.zjc.keepwork.fragment.under_bar_fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.qweather.plugin.view.HeContent;
import com.qweather.plugin.view.HorizonView;
import com.qweather.plugin.view.LeftLargeView;
import com.qweather.plugin.view.QWeatherConfig;
import com.xuexiang.xui.widget.textview.MarqueeTextView;
import com.xuexiang.xui.widget.textview.marqueen.DisplayEntity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zjc.keepwork.R;
import com.zjc.keepwork.activity.DepositActivity;
import com.zjc.keepwork.activity.MainActivity;
import com.zjc.keepwork.adapter.FunctionAdapter;
import com.zjc.keepwork.adapter.GoodVoAdapter;
import com.zjc.keepwork.adapter.pojo.Function;
import com.zjc.keepwork.adapter.pojo.GoodsVo;
import com.zjc.keepwork.service.IGoodService;
import com.zjc.keepwork.service.imp.GoodServiceImpl;
import com.zjc.keepwork.util.MyApplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.zjc.keepwork.activity.CommunicationActivity;


public class MainFragment extends Fragment implements AMapLocationListener {
    MainActivity mainactivity;
    public MainFragment(MainActivity mainactivity) {
        this.mainactivity = mainactivity;
    }
    //轮播图
    @BindView(R.id.imageBanner)
    Banner banner;
    //定位
    @BindView(R.id.main_city)
    TextView main_city;
    //定位需要的声明
    @BindView(R.id.tv_marquee)
    MarqueeTextView tv_marquee;
    @BindView(R.id.main_fl_fun)
    RecyclerView main_fl_fun;
    @BindView(R.id.ll_view)
    LeftLargeView leftLargeView;
    @BindView(R.id.main_seckill_activity)
    RecyclerView main_seckill_activity;
    private IGoodService goodService;
    private FunctionAdapter functionAdapter;
    private GoodVoAdapter goodVoAdapter;
    private AMapLocationClient mLocationClient = null;//定位发起端
    private AMapLocationClientOption mLocationOption = null;//定位参数
    Unbinder unbinder;
    private SeckillFragment seckillFragment;
    private MineFragment mineFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initWeather();
        //initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main, container, false);
        unbinder= ButterKnife.bind(this,view);
        initBanner();
        initLoc();
        initMarqueeTextView();
        initSeckillGoods();
        List<Function> list=initFun();
        functionAdapter=new FunctionAdapter(R.layout.fun_viewholder,list);
        functionAdapter.setOnItemClickListener(itemId -> {
            //我的余额启动DepositActivity
            if ("5".equals(itemId)){
                Intent intent=new Intent(getActivity(), DepositActivity.class);
                startActivity(intent);
            }
            //秒杀活动直接跳转至seckillfragment
            if ("6".equals(itemId)){
                MainActivity mainActivity= (MainActivity) getActivity();
                seckillFragment=new SeckillFragment(mainActivity);
                mainActivity.resetImageAndTextColor();
                mainActivity.loadFragment(seckillFragment);
            }
            //打开个人中心
            if ("7".equals(itemId)){
                MainActivity mainActivity= (MainActivity) getActivity();
                mineFragment=new MineFragment(mainActivity);
                mainActivity.resetImageAndTextColor();
                mainActivity.loadFragment(mineFragment);
            }
            //打开与客服的聊天界面
            if ("8".equals(itemId)){
                Intent intent=new Intent(getActivity(), CommunicationActivity.class);
                startActivity(intent);
            }
        });
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),4);
        main_fl_fun.setAdapter(functionAdapter);
        main_fl_fun.setLayoutManager(gridLayoutManager);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        main_seckill_activity.setLayoutManager(layoutManager2);
        main_seckill_activity.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        initWeather();
        return view;
    }

    private void initSeckillGoods() {
        goodService = new GoodServiceImpl(null,this);
        goodService.getGoodsVo();
    }

    private void initWeather() {
        QWeatherConfig.init("dd007c2677d345e794fc3e28679405fd",MyApplication.getCity());
        //左侧大布局右侧双布局控件
        //获取左侧大布局
        LinearLayout leftLayout = leftLargeView.getLeftLayout();
        //获取右上布局
        LinearLayout rightTopLayout = leftLargeView.getRightTopLayout();
        //获取右下布局
        LinearLayout rightBottomLayout = leftLargeView.getRightBottomLayout();
        //设置布局的背景圆角角度（单位：dp），颜色，边框宽度（单位：px），边框颜色
        leftLargeView.setStroke(5, Color.parseColor("#313a44"), 1, Color.BLACK);
        //添加温度描述到左侧大布局
        //第一个参数为需要加入的布局
        //第二个参数为文字大小，单位：sp
        //第三个参数为文字颜色，默认白色
        leftLargeView.addTemp(leftLayout, 40, Color.WHITE);
        //添加温度图标到右上布局，第二个参数为图标宽高（宽高1：1，单位：dp）
        leftLargeView.addWeatherIcon(rightTopLayout, 14);
        //添加文字AQI到右上布局
        leftLargeView.addAqiText(rightTopLayout, 14);
        //添加空气质量到右上布局
        leftLargeView.addAqiQlty(rightTopLayout, 14);
        //添加空气质量数值到右上布局
        leftLargeView.addAqiNum(rightTopLayout, 14);
        //添加地址信息到右上布局
        leftLargeView.addLocation(rightTopLayout, 14, Color.WHITE);
        //添加天气描述到右下布局
        leftLargeView.addCond(rightBottomLayout, 14, Color.WHITE);
        //添加风向图标到右下布局
        leftLargeView.addWindIcon(rightBottomLayout, 14);
        //添加风力描述到右下布局
        leftLargeView.addWind(rightBottomLayout, 14, Color.WHITE);
        //添加降雨图标到右下布局
        leftLargeView.addRainIcon(rightBottomLayout, 14);
        //添加降雨描述到右下布局
        leftLargeView.addRainDetail(rightBottomLayout, 14, Color.WHITE);
        //设置控件的对齐方式，默认居中
        leftLargeView.setViewGravity(HeContent.GRAVITY_LEFT);
        //显示布局
        leftLargeView.show();
    }

    public void initBanner(){
        List images=new ArrayList();
        images.add(R.drawable.main_bg1);
        images.add(R.drawable.main_bg2);
        images.add(R.drawable.main_bg3);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(getContext()).load(path).into(imageView);
            }
        });
        banner.setImages(images);
        banner.start();
    }
    private void initLoc() {
        //初始化定位
        try {
            AMapLocationClient.updatePrivacyShow(getContext(),true,true);
            AMapLocationClient.updatePrivacyAgree(getContext(),true);
            mLocationClient = new AMapLocationClient(getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //设置定位回调监听
        mLocationClient.setLocationListener(this::onLocationChanged);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(true);
        mLocationOption.setInterval(2000);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }
    public void initMarqueeTextView(){
        tv_marquee.bringToFront();
        List<String> list=new ArrayList<>();
        list.add("三湘银行（BANK OF SANXIANG）是湖南省和中部地区首家民营银行，由三一集团、汉森制药等9家民营企业作为发起人股东共同发起设立。于2016年12月26日正式开业，注册资本30亿元，注册地湖南长沙。");
        tv_marquee.setOnMarqueeListener(new MarqueeTextView.OnMarqueeListener() {
            @Override
            public DisplayEntity onStartMarquee(DisplayEntity displayMsg, int index) {
                if (displayMsg.toString().equals("离离原上草，一岁一枯荣。")) {
                    return null;
                } else {
                    //ToastUtils.toast("开始滚动：" + displayMsg.toString());
                    return displayMsg;
                }
            }
            @Override
            public List<DisplayEntity> onMarqueeFinished(List<DisplayEntity> displayDatas) {
                //ToastUtils.toast("一轮滚动完毕！");
                return displayDatas;
            }
        });
        tv_marquee.startSimpleRoll(list);
    }
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                double latitude = aMapLocation.getLatitude();//获取纬度
                double longitude = aMapLocation.getLongitude();//获取经度
                MyApplication.setLat(String.valueOf(latitude));
                MyApplication.setLot(String.valueOf(longitude));
                Log.i("zjc",latitude+","+longitude);
                aMapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);//定位时间

                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码
                MyApplication.setCity(aMapLocation.getCity());
                main_city.setText(aMapLocation.getCity());

            }
        } else {
            //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
            Log.e("AmapError", "location Error, ErrCode:"
                    + aMapLocation.getErrorCode() + ", errInfo:"
                    + aMapLocation.getErrorInfo());
            Log.d("AmapError", "location Error, ErrCode:"
                    + aMapLocation.getErrorCode() + ", errInfo:"
                    + aMapLocation.getErrorInfo());
            Log.w("AmapError", "location Error, ErrCode:"
                    + aMapLocation.getErrorCode() + ", errInfo:"
                    + aMapLocation.getErrorInfo());
        }
    }
    public List<Function>  initFun(){
        List list=new ArrayList();
//        Function function=new Function(1,"活动打卡");
//        Function function1=new Function(2,"活动公示");
//        Function function2=new Function(3,"志愿团队");
//        Function function3=new Function(4,"排行榜");
        Function function4=new Function(5,"我的余额");
        Function function5=new Function(6,"秒杀活动");
        Function function6=new Function(7,"个人中心");
        Function function7=new Function(8,"联系客服");
//        list.add(function);
//        list.add(function1);
//        list.add(function2);
//        list.add(function3);
        list.add(function4);
        list.add(function5);
        list.add(function6);
        list.add(function7);
        return list;
    }
    public void goodsVoCallBack(List<GoodsVo> list){
        getActivity().runOnUiThread(() -> {
            goodVoAdapter=new GoodVoAdapter(list);
            main_seckill_activity.setAdapter(goodVoAdapter);
        });
    }
}
