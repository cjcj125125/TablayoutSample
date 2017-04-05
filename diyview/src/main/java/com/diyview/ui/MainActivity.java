package com.diyview.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.jump)
    Button jump;

//    @butterknife.BindView(R.id.pieView)
//    PieView pieView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TwoActivity.class));
            }
        });
    }

    private void initData() {
//        List<PieData> pieDataList = new ArrayList<>();
//        for (int i = 0; i <10; i++) {
//            PieData pieData = new PieData("测试" + i, 50 + i);
//            pieDataList.add(pieData);
//        }
//        pieView.setData(pieDataList);
    }

}
