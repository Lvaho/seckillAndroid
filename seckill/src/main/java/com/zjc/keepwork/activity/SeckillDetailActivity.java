package com.zjc.keepwork.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xuexiang.xutil.tip.ToastUtils;
import com.zjc.keepwork.R;
import com.zjc.keepwork.service.ISeckillService;
import com.zjc.keepwork.service.imp.SeckillServiceImpl;
import com.zjc.keepwork.util.MyApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SeckillDetailActivity extends AppCompatActivity {

    @BindView(R.id.seckill_detail_goodsname_tv)
    TextView seckill_detail_goodsname_tv;
    @BindView(R.id.seckill_detali_goodsprice_tv)
    TextView seckill_detail_goodsprice_tv;
    @BindView(R.id.seckill_deatil_starttime_tv)
    TextView seckill_detail_starttime_tv;
    @BindView(R.id.seckill_detail_endtime_tv)
    TextView seckill_detail_endtime_tv;
    @BindView(R.id.seckill_detail_goodsdetail_tv)
    TextView seckill_detail_goodsdetail_tv;
    @BindView(R.id.seckill_detail_stockcount_tv)
    TextView seckill_detail_stockcount_tv;
    @BindView(R.id.seckill_detali_seckillprice_tv)
    TextView seckill_detail_seckillprice_tv;
    @BindView(R.id.seckill_detail_btn)
    Button seckill_detail_btn;
    @BindView(R.id.seckill_getdetail_btn)
    Button seckill_getdetail_btn;
    ISeckillService seckillService;
    Unbinder unbinder;
    Date date =new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seckill_detail);
        unbinder= ButterKnife.bind(this);
        try {
            initSeckillDetail();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void initSeckillDetail() throws ParseException {
        Bundle bundle = getIntent().getExtras();
        String goodsname = bundle.getString("goodsname");
        String goodsprice = bundle.getString("goodsprice");
        String seckillprice = bundle.getString("seckillprice");
        String goodsdetail = bundle.getString("goodsdetail");
        String stockcount = bundle.getString("stockcount");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date starttime = formatter.parse(bundle.getString("starttime"));
        Date endtime = formatter.parse(bundle.getString("endtime"));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                seckill_detail_goodsname_tv.setText(goodsname);
                seckill_detail_goodsprice_tv.setText(goodsprice);
                seckill_detail_goodsdetail_tv.setText(goodsdetail);
                seckill_detail_stockcount_tv.setText("???????????????:"+stockcount);
                seckill_detail_starttime_tv.setText(starttime.toString());
                seckill_detail_endtime_tv.setText(endtime.toString());
                seckill_detail_seckillprice_tv.setText(seckillprice);
                if (date.after(endtime)){
                    seckill_detail_btn.setText("?????????????????????????????????");

                } else if (date.after(starttime)&date.before(endtime)){
                    seckill_detail_btn.setEnabled(true);
                }else if (date.before(starttime)){
                    Long timecount = starttime.getTime()-date.getTime();
                    CountDownTimer timer = new CountDownTimer(timecount, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            seckill_detail_btn.setEnabled(false);
                            seckill_detail_btn.setText(String.format("??????(%d)?????????",millisUntilFinished/1000));
                        }
                        @Override
                        public void onFinish() {
                            seckill_detail_btn.setEnabled(true);
                            seckill_detail_btn.setText("????????????");
                        }
                    };
                    timer.start();
                }
            }
        });
    }

    @OnClick({R.id.seckill_detail_btn,R.id.seckill_getdetail_btn})
    public void viewOnclick(View view){
        switch (view.getId()){
            case R.id.seckill_detail_btn:
                doSeckill();
                break;
            case R.id.seckill_getdetail_btn:
                Bundle bundle = getIntent().getExtras();
                String goodsid = bundle.getString("id");
                seckillService.getResult(goodsid);
                break;
        }
    }

    private void doSeckill() {
        seckillService = new SeckillServiceImpl(this);
        Bundle bundle = getIntent().getExtras();
        String goodsid = bundle.getString("id");
        seckillService.getSeckillPath(goodsid);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                seckill_detail_btn.setEnabled(false);
                seckill_detail_btn.setVisibility(View.INVISIBLE);
                ToastUtils.toast("???????????????????????????");
                CountDownTimer timer = new CountDownTimer(1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        seckill_getdetail_btn.setText(String.format("??????(%d)?????????????????????",millisUntilFinished/1000));
                    }
                    @Override
                    public void onFinish() {
                        seckill_getdetail_btn.setText("????????????");
                        seckill_getdetail_btn.setEnabled(true);
                    }
                };
                timer.start();
            }
        });
    }

    public void getResultCallBack(String orderid){
        Intent intent=new Intent(SeckillDetailActivity.this, OrderDetailActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("orderid",orderid);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}