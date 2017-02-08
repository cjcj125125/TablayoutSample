package com.tablayout.sample;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tablayout.adapter.TabPagerAdapter;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;

/*****
 * 此demo引用简书作者 尹star  的TabLayout高端用法（http://www.jianshu.com/p/be1e8a1da639）
 * 如有冒犯敬请见谅
 ***/
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private TabPagerAdapter pageradapter;
    private String[] titles = {"QQ", "微信", "微博", "支付宝"};
    private int[] imgs = {R.drawable.qq_select, R.drawable.wx_select, R.drawable.wb_select, R.drawable.alipay_select};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        pageradapter = new TabPagerAdapter(getSupportFragmentManager(), this);
        viewpager.setOffscreenPageLimit(1);
        viewpager.setAdapter(pageradapter);
        tablayout.setupWithViewPager(viewpager);
        setIndicator(this,tablayout,10,10);//设置tabIndicator长度
        for (int i = 0; i < tablayout.getTabCount(); i++) {
            tablayout.getTabAt(i).setCustomView(getTabView(i));
        }
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeTabStatus(tab, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                changeTabStatus(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public View getTabView(final int position) {
        final View view = LayoutInflater.from(this).inflate(R.layout.item_tab, null);
        TextView txtTitle = (TextView) view.findViewById(R.id.title);
        final ImageView imgicon = (ImageView) view.findViewById(R.id.icon);
        imgicon.setImageResource(imgs[position]);
        txtTitle.setText(titles[position]);
        //这里可以不写点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPropertyAnim(imgicon);//实现切换动画
                viewpager.setCurrentItem(position);
            }
        });
        return view;
    }

    private void startPropertyAnim(ImageView v) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(v, "rotation", 180f, 360f);
        anim.setDuration(500);
        anim.start();
    }

    private void changeTabStatus(TabLayout.Tab tab, boolean selected) {
        View view = tab.getCustomView();
        final ImageView imgTitle = (ImageView) view.findViewById(R.id.icon);
        TextView txtTitle = (TextView) view.findViewById(R.id.title);
        if (selected) {
            txtTitle.setTextColor(ContextCompat.getColor(this, R.color.textline));
            startPropertyAnim(imgTitle);
        } else {
            txtTitle.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        }
    }

    //设置指示器的长度
    public void setIndicator(MainActivity context, TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        tabStrip.setAccessible(true);
        LinearLayout ll_tab = null;
        try {
            ll_tab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        int left = (int) (context.getResources().getDisplayMetrics().density * leftDip);
        int right = (int) (context.getResources().getDisplayMetrics().density * rightDip);

        for (int i = 0; i < ll_tab.getChildCount(); i++) {
            View child = ll_tab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

}
