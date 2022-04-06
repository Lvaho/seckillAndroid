package com.zjc.keepwork.fragment.under_bar_fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


import com.makeramen.roundedimageview.RoundedImageView;
import com.xuexiang.xui.widget.button.switchbutton.SwitchButton;
import com.xuexiang.xui.widget.dialog.materialdialog.DialogAction;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.zjc.keepwork.R;
import com.zjc.keepwork.activity.DepositActivity;
import com.zjc.keepwork.activity.DepositRechargeActivity;
import com.zjc.keepwork.activity.LaunchActivity;
import com.zjc.keepwork.activity.MainActivity;
import com.zjc.keepwork.activity.SetPasswordActivity;
import com.zjc.keepwork.activity.UserDetailActivity;
import com.zjc.keepwork.util.MyApplication;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.wildfire.chat.kit.qrcode.ScanQRCodeActivity;


public class MineFragment extends Fragment {
    MainActivity mainActivity;
    Unbinder unbinder;
    @BindView(R.id.mine_deposit_rp_ll)
    LinearLayout mine_deposit_rp_ll;
    @BindView(R.id.message_item_ll)
    LinearLayout message_item_ll;
    @BindView(R.id.message_center_ll)
    LinearLayout message_center_ll;
    @BindView(R.id.use_instruction_ll)
    LinearLayout use_instruction_ll;
    @BindView(R.id.my_exit_ll)
    LinearLayout my_exit_ll;
    @BindView(R.id.change_password_ll)
    LinearLayout change_password_ll;
    @BindView(R.id.change_head_ll)
    LinearLayout change_head_ll;
    @BindView(R.id.user_detail_ll)
    LinearLayout user_detail_ll;
    @BindView(R.id.user_name)
    TextView user_name;
    @BindView(R.id.head_view)
    RoundedImageView head_view;
    private SeckillFragment seckillFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public MineFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        user_name.setText(MyApplication.getUser_name());
        return view;
    }


    @OnClick({R.id.mine_deposit_rp_ll,R.id.message_item_ll,R.id.message_center_ll,
            R.id.use_instruction_ll,R.id.my_exit_ll,
            R.id.change_password_ll,R.id.change_head_ll,R.id.user_detail_ll})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.mine_deposit_rp_ll:
                myDeposit();
                break;
            case R.id.message_item_ll:
                Intent intent = new Intent(getActivity(), DepositRechargeActivity.class);
                startActivity(intent);
                break;
            case R.id.message_center_ll:
                MainActivity mainActivity= (MainActivity) getActivity();
                seckillFragment=new SeckillFragment(mainActivity);
                mainActivity.resetImageAndTextColor();
                mainActivity.loadFragment(seckillFragment);
                break;
            case R.id.use_instruction_ll:
                //useInstruction();
                break;
            case R.id.my_exit_ll:
                logout();
                break;
            case R.id.change_password_ll:
                toSetPassword();
                break;
            case R.id.change_head_ll:
                Intent intent1 = new Intent(getActivity(), ScanQRCodeActivity.class);
                startActivity(intent1);
                break;
            case R.id.user_detail_ll:
                toUserDeatil();
                break;
        }
    }

    public void toSetPassword(){
        Intent intent=new Intent(MyApplication.getContext(), SetPasswordActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //退出登录
    public void logout(){
        MyApplication.setCookie("");
        MyApplication.setUser_id("");
        MyApplication.setUser_name("");
        Intent intent=new Intent(MyApplication.getContext(), LaunchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getContext().startActivity(intent);
        getActivity().finish();
    }
    //我的余额
    public void myDeposit(){
        Intent intent=new Intent(getActivity(), DepositActivity.class);
        startActivity(intent);
    }
    //用户详情
    public void toUserDeatil(){
        Intent intent=new Intent(MyApplication.getContext(), UserDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}