package zjc.seckill.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zjc.seckill.R;
import zjc.seckill.util.MyApplication;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.bt_tc)
    Button btTc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_tc)
    public void onClick() {
        MyApplication.clearPref();
        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        Toast.makeText(MainActivity.this,"退出登录成功！",Toast.LENGTH_LONG).show();
        finish();
    }
}