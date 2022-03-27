package com.zjc.keepwork.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjc.keepwork.R;
import com.zjc.keepwork.pojo.GoodsDetailVo;
import com.zjc.keepwork.service.IOrderService;
import com.zjc.keepwork.service.imp.OrderServiceImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OrderDetailActivity extends AppCompatActivity {

    @BindView(R.id.order_detail_goodsname_tv)
    TextView order_detail_goodsname_tv;
    @BindView(R.id.order_detail_back)
    ImageView order_detail_back;
    @BindView(R.id.order_detali_goodsprice_tv)
    TextView order_detali_goodsprice_tv;
    @BindView(R.id.order_detail_orderstatus_tv)
    TextView order_detail_orderstatus_tv;
    @BindView(R.id.order_deatil_ordertime_tv)
    TextView order_deatil_ordertime_tv;
    @BindView(R.id.order_detail_btn)
    Button order_detail_btn;
    IOrderService orderService;
    Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        unbinder= ButterKnife.bind(this);
        initOrder();
    }

    private void initOrder() {
        Bundle bundle = getIntent().getExtras();
        String orderid = bundle.getString("orderid");
        orderService =new OrderServiceImpl(OrderDetailActivity.this);
        orderService.getOrderByOrderId(orderid);
    }

    @OnClick({R.id.order_detail_btn,R.id.order_detail_back})
    public void viewOnclick(View view){
        switch (view.getId()){
            case R.id.order_detail_back:
                finish();
                break;
            case R.id.order_detail_btn:
                Bundle bundle = getIntent().getExtras();
                String orderid = bundle.getString("orderid");
                orderService.payOrder(orderid);
                break;
        }
    }

    public void OrderCallBack(GoodsDetailVo goodsDetailVo) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                order_detail_goodsname_tv.setText(goodsDetailVo.getGoodsName());
                order_deatil_ordertime_tv.setText(goodsDetailVo.getCreatetime().toString());
                order_detali_goodsprice_tv.setText(goodsDetailVo.getGoodsPrice());
                if(goodsDetailVo.getGoodsStatus().equals("0")){
                    order_detail_orderstatus_tv.setText("未支付");
                    order_detail_btn.setEnabled(true);
                }else if (goodsDetailVo.getGoodsStatus().equals("1")){
                    order_detail_orderstatus_tv.setText("已支付");
                }
            }
        });
    }
}