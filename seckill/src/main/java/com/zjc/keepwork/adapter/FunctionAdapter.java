package com.zjc.keepwork.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zjc.keepwork.R;
import com.zjc.keepwork.adapter.pojo.Function;

import java.util.List;

public class FunctionAdapter extends BaseQuickAdapter<Function, BaseViewHolder> {
    public FunctionAdapter(int layoutResId, @Nullable List<Function> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Function item) {
        RoundedImageView view = helper.getView(R.id.img_fun);
        if (1==item.getPos()){
            helper.setBackgroundRes(R.id.img_fun,R.drawable.fun1);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClickListener(String.valueOf(item.getPos()));
                }
            });
        }else if (2==item.getPos()){
            helper.setBackgroundRes(R.id.img_fun,R.drawable.fun2);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClickListener(String.valueOf(item.getPos()));
                }
            });
        }
        else if (3==item.getPos()){
            helper.setBackgroundRes(R.id.img_fun,R.drawable.fun3);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClickListener(String.valueOf(item.getPos()));
                }
            });
        }
        else if (4==item.getPos()){
            helper.setBackgroundRes(R.id.img_fun,R.drawable.fun4);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClickListener(String.valueOf(item.getPos()));
                }
            });
        }
        else if (5==item.getPos()){
            helper.setBackgroundRes(R.id.img_fun,R.drawable.fun5);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClickListener(String.valueOf(item.getPos()));
                }
            });
        }
        else if (6==item.getPos()){
            helper.setBackgroundRes(R.id.img_fun,R.drawable.fun6);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClickListener(String.valueOf(item.getPos()));
                }
            });
        }
        else if (7==item.getPos()){
            helper.setBackgroundRes(R.id.img_fun,R.drawable.fun7);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClickListener(String.valueOf(item.getPos()));
                }
            });
        }
        else if (8==item.getPos()){
            helper.setBackgroundRes(R.id.img_fun,R.drawable.fun8);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClickListener(String.valueOf(item.getPos()));
                }
            });
        }
        helper.setText(R.id.text_fun,item.getFunctionName());
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClickListener(String itemId);
    }
}
