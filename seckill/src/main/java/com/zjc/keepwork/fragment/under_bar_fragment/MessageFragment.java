package com.zjc.keepwork.fragment.under_bar_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.zjc.keepwork.R;
import com.zjc.keepwork.activity.DepositActivity;
import com.zjc.keepwork.activity.DepositRechargeActivity;
import com.zjc.keepwork.activity.MainActivity;
import com.zjc.keepwork.pojo.DepositVo;
import com.zjc.keepwork.service.IDepositService;
import com.zjc.keepwork.service.imp.DepositServiceImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MessageFragment extends Fragment {
    MainActivity mainActivity;
    Unbinder unbinder;
    IDepositService depositService;
    public MessageFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    @BindView(R.id.deposit_in_fragment)
    TextView depositTextview;
    @BindView(R.id.recharge_button_in_fragment)
    Button rechargebutton;
    @BindView(R.id.refresh_deposit_button_in_fragment)
    Button refreshbutton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        unbinder = ButterKnife.bind(this, view);
        initdeposit();
        return view;
    }

    private void initdeposit() {
        depositService = new DepositServiceImpl(this);
        depositService.findDepositByCookieinFragment();
    }

    public void depositcallback(DepositVo depositVo) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                depositTextview.setText("￥"+depositVo.getDeposit().toString());
            }
        });
    }
    @OnClick({R.id.recharge_button_in_fragment,R.id.refresh_deposit_button_in_fragment})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.recharge_button_in_fragment:
                Intent intent = new Intent(getActivity(), DepositRechargeActivity.class);
                startActivity(intent);
                break;
            case R.id.refresh_deposit_button_in_fragment:
                depositService = new DepositServiceImpl(this);
                depositService.findDepositByCookieinFragment();
        }
    }
}