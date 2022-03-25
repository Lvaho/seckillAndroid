package com.zjc.keepwork.fragment.under_bar_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zjc.keepwork.R;
import com.zjc.keepwork.activity.MainActivity;
import com.zjc.keepwork.adapter.GoodVoAdapter;
import com.zjc.keepwork.adapter.pojo.GoodsVo;
import com.zjc.keepwork.service.IGoodService;
import com.zjc.keepwork.service.imp.GoodServiceImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SeckillFragment extends Fragment {
    MainActivity mainActivity;
    private IGoodService goodService;
    public SeckillFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    Unbinder unbinder;
    private GoodVoAdapter goodVoAdapter;
    @BindView(R.id.fragment_seckill_activity)
    RecyclerView fragment_seckill_activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seckill, container, false);
        unbinder = ButterKnife.bind(this, view);
        initSeckillGoods();
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        fragment_seckill_activity.setLayoutManager(layoutManager2);
        fragment_seckill_activity.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        return view;
    }
    private void initSeckillGoods() {
        goodService = new GoodServiceImpl(this);
        goodService.getAllGoodsVo();
    }
    public void goodVoCallBack(List<GoodsVo> list){
        getActivity().runOnUiThread(() -> {
            goodVoAdapter=new GoodVoAdapter(list);
            fragment_seckill_activity.setAdapter(goodVoAdapter);
        });
    }

}