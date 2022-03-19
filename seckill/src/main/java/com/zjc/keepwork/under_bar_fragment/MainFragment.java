package com.zjc.keepwork.under_bar_fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zjc.keepwork.R;
import com.zjc.keepwork.activity.MainActivity;
import com.zjc.keepwork.util.MyApplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainFragment extends Fragment implements AMapLocationListener {
    MainActivity activity;
    public MainFragment(MainActivity activity) {
        this.activity = activity;
    }
    //轮播图
    @BindView(R.id.imageBanner)
    Banner banner;
    //定位
    @BindView(R.id.main_city)
    TextView main_city;
    //定位需要的声明
    private AMapLocationClient mLocationClient = null;//定位发起端
    private AMapLocationClientOption mLocationOption = null;//定位参数
    Unbinder unbinder;

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
        return view;
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
}
