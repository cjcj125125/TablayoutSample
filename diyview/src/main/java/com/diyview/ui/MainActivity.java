package com.diyview.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.diyview.diyview.PieView;
import com.diyview.entity.PieData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    @butterknife.BindView(R.id.pieView)
//    PieView pieView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  butterknife.ButterKnife.bind(this);
        //initData();
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
