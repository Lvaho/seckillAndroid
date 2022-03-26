package com.zjc.keepwork.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zjc.keepwork.R;
import com.zjc.keepwork.activity.SeckillDetailActivity;
import com.zjc.keepwork.adapter.pojo.GoodsVo;
import com.zjc.keepwork.util.DateUtil;
import com.zjc.keepwork.util.MyApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class GoodVoAdapter extends BaseQuickAdapter<GoodsVo, BaseViewHolder> {

    private Date date =new Date();
    public GoodVoAdapter(@Nullable List<GoodsVo> data) {
        super(R.layout.seckill_viewholder, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsVo item) {
        helper.setText(R.id.seckill_detail_tv,item.getGoodsDetail());
        helper.setText(R.id.seckill_endtime_tv,"结束时间:"+DateUtil.formdate(item.getEndDate()));
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
                Log.i("zjc",item.getGoodsName());
                Intent intent=new Intent(MyApplication.getContext(), SeckillDetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String startdate = formatter.format(item.getStartDate());
                String enddate =formatter.format(item.getEndDate());
                Bundle bundle=new Bundle();
                bundle.putString("id",String.valueOf(item.getId()));
                bundle.putString("goodsname",item.getGoodsName());
                bundle.putString("goodsprice",String.valueOf(item.getGoodsPrice()));
                bundle.putString("seckillprice",String.valueOf(item.getSeckillPrice()));
                bundle.putString("starttime",startdate);
                bundle.putString("endtime",enddate);
                bundle.putString("goodsdetail",item.getGoodsDetail());
                bundle.putString("stockcount",String.valueOf(item.getStockCount()));
                intent.putExtras(bundle);
                MyApplication.getContext().startActivity(intent);
            }
        });
    }
    private GoodVoAdapter.OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onItemClickListener(GoodsVo goodsVo);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
