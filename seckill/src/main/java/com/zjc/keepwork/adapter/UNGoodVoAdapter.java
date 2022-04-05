package com.zjc.keepwork.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xuexiang.xutil.tip.ToastUtils;
import com.zjc.keepwork.R;
import com.zjc.keepwork.activity.SeckillDetailActivity;
import com.zjc.keepwork.adapter.pojo.GoodsVo;
import com.zjc.keepwork.util.DateUtil;
import com.zjc.keepwork.util.MyApplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UNGoodVoAdapter extends BaseQuickAdapter<GoodsVo, BaseViewHolder> {

    private Date date =new Date();
    public UNGoodVoAdapter(@Nullable List<GoodsVo> data) {
        super(R.layout.seckill_viewholder, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsVo item) {
        helper.setText(R.id.seckill_detail_tv,item.getGoodsDetail());
        helper.setText(R.id.seckill_endtime_tv,"结束时间:"+ DateUtil.formdate(item.getEndDate()));
        helper.setText(R.id.seckill_stockcount,"库存："+String.valueOf(item.getStockCount()));
        if (item.getStartDate().after(date)){
            helper.setImageResource(R.id.seckill_status_img,R.drawable.notstart);
        }else if (item.getStartDate().after(date)&item.getEndDate().before(date)){
            helper.setImageResource(R.id.seckill_status_img,R.drawable.doing);
        }else if (item.getEndDate().before(date)){
            helper.setImageResource(R.id.seckill_status_img,R.drawable.end);
        }
        helper.getView(R.id.seckill_deatil_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.toast("你没有权限进行秒杀");
            }
        });
    }
    private UNGoodVoAdapter.OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onItemClickListener(GoodsVo goodsVo);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
