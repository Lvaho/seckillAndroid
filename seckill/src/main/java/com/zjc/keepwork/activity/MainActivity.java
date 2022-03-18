package com.zjc.keepwork.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.zjc.keepwork.R;
import com.zjc.keepwork.under_bar_fragment.DepositFragment;
import com.zjc.keepwork.under_bar_fragment.MainFragment;
import com.zjc.keepwork.under_bar_fragment.MessageFragment;
import com.zjc.keepwork.under_bar_fragment.MineFragment;
import com.zjc.keepwork.under_bar_fragment.SeckillFragment;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //四个导航栏的fragment
    private DepositFragment depositFragment;
    private SeckillFragment seckillFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;
    private MainFragment mainFragment;
    //4个标签tab布局控件
    private LinearLayout tab_map_ll;
    private LinearLayout tab_message_ll;
    private LinearLayout tab_task_ll;
    private LinearLayout tab_mine_ll;
    private LinearLayout tab_carme_ll;
    //4个图片按钮控件
    private ImageButton tab_map_ib;
    private ImageButton tab_message_ib;
    private ImageButton tab_task_ib;
    private ImageButton tab_mine_ib;
    private ImageButton tab_carme_ib;
    //4个文本控件
    private TextView tab_map_tv;
    private TextView tab_message_tv;
    private TextView tab_task_tv;
    private TextView tab_mine_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPower();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initControls();
        initControlsEvent();
    }


    //申请权限
    public void requestPower() {
        //判断是否已经赋予权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //如果应用之前请求过此权限但用户拒绝了请求，此方法将返回 true。
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {//这里可以写个对话框之类的项向用户解释为什么要申请权限，并在对话框的确认键后续再次申请权限.它在用户选择"不再询问"的情况下返回false
            } else {
                //申请权限，字符串数组内是一个或多个要申请的权限，1是申请权限结果的返回参数，在onRequestPermissionsResult可以得知申请结果
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1);
            }
        }//判断是否已经赋予权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //如果应用之前请求过此权限但用户拒绝了请求，此方法将返回 true。
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {//这里可以写个对话框之类的项向用户解释为什么要申请权限，并在对话框的确认键后续再次申请权限.它在用户选择"不再询问"的情况下返回false
            } else {
                //申请权限，字符串数组内是一个或多个要申请的权限，1是申请权限结果的返回参数，在onRequestPermissionsResult可以得知申请结果
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,}, 1);
            }
        }
    }

    //初始化点击事件
    private void initControls(){
        tab_map_ll=findViewById(R.id.tab_map_ll);
        tab_message_ll=findViewById(R.id.tab_message_ll);
        tab_task_ll=findViewById(R.id.tab_task_ll);
        tab_mine_ll=findViewById(R.id.tab_mine_ll);
        tab_map_ib=findViewById(R.id.tab_map_ib);
        tab_message_ib=findViewById(R.id.tab_message_ib);
        tab_task_ib=findViewById(R.id.tab_task_ib);
        tab_mine_ib=findViewById(R.id.tab_mine_ib);
        tab_map_tv=findViewById(R.id.tab_map_tv);
        tab_message_tv=findViewById(R.id.tab_message_tv);
        tab_task_tv=findViewById(R.id.tab_task_tv);
        tab_mine_tv=findViewById(R.id.tab_mine_tv);
        tab_carme_ll=findViewById(R.id.tab_carme_ll);
        tab_carme_ib=findViewById(R.id.tab_carme_ib);
        //四个Fragment对象
        depositFragment =new DepositFragment();
        messageFragment=new MessageFragment(this);
        seckillFragment=new SeckillFragment(this);
        mineFragment=new MineFragment(this);
        mainFragment=new MainFragment(this);
    }

    //点击事件监听器附着
    private void initControlsEvent(){
        tab_map_ll.setOnClickListener(this);
        tab_message_ll.setOnClickListener(this);
        tab_task_ll.setOnClickListener(this);
        tab_mine_ll.setOnClickListener(this);
        tab_carme_ll.setOnClickListener(this);
    }

    //根据点击不同的东西做出对应响应
    @Override
    public void onClick(View view) {
        resetImageAndTextColor();
        switch (view.getId()){
            case R.id.tab_map_ll:
                loadFragment(mainFragment);
                break;
            case R.id.tab_message_ll:
                loadFragment(messageFragment);
                break;
            case R.id.tab_task_ll:
                loadFragment(seckillFragment);
                break;
            case R.id.tab_mine_ll:
                loadFragment(mineFragment);
                break;
            case R.id.tab_carme_ll:
                //takePhoto();
                //openCamera();
                break;
        }
    }

    //通过修改按钮颜色增强交互感
    public void resetImageAndTextColor(){
        tab_map_ib.setImageResource(R.drawable.map_n);
        tab_map_tv.setTextColor(Color.parseColor("#272727"));
        tab_message_ib.setImageResource(R.drawable.message_n);
        tab_message_tv.setTextColor(Color.parseColor("#272727"));
        tab_task_ib.setImageResource(R.drawable.task_n);
        tab_task_tv.setTextColor(Color.parseColor("#272727"));
        tab_mine_ib.setImageResource(R.drawable.mine_n);
        tab_mine_tv.setTextColor(Color.parseColor("#272727"));
    }

    //加载Fragment
    public void loadFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        //用四种fragment视图替换main.xml中的控件
        fragmentTransaction.replace(R.id.main_fl,fragment);
        fragmentTransaction.commit();
        if (fragment instanceof DepositFragment){
            tab_map_ib.setImageResource(R.drawable.map_y);
            tab_map_tv.setTextColor(Color.parseColor("#D9261C"));
        }else if (fragment instanceof MessageFragment){
            //alterProgress();
            tab_message_ib.setImageResource(R.drawable.message_y);
            tab_message_tv.setTextColor(Color.parseColor("#D9261C"));
        }else if (fragment instanceof SeckillFragment){
            //alterProgress();
            tab_task_ib.setImageResource(R.drawable.task_y);
            tab_task_tv.setTextColor(Color.parseColor("#D9261C"));
        }else if (fragment instanceof MineFragment){
            //alterProgress();
            tab_mine_ib.setImageResource(R.drawable.mine_y);
            tab_mine_tv.setTextColor(Color.parseColor("#D9261C"));
        }else if (fragment instanceof MainFragment){
            //alterProgress();
            tab_map_ib.setImageResource(R.drawable.map_y);
            tab_map_tv.setTextColor(Color.parseColor("#D9261C"));
        }
    }

}