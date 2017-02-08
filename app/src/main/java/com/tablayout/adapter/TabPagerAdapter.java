package com.tablayout.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.CharacterStyle;
import android.text.style.ImageSpan;

import com.tablayout.fragmets.AliPayFragment;
import com.tablayout.fragmets.QqFragment;
import com.tablayout.fragmets.WbFragment;
import com.tablayout.fragmets.WxFragment;
import com.tablayout.sample.R;

/**
 * Created by Right on 2017/2/4.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {
    private Fragment [] fragments={QqFragment.newInstance(), WxFragment.newInstance(), WbFragment.newInstance(), AliPayFragment.newInstance()};
    private String[] titles={"QQ","微信","微博","支付宝"};
    private Context context;
    private int[] imgs = {R.drawable.qq_select,R.drawable.wx_select,R.drawable.wb_select,R.drawable.alipay_select};

    public TabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    //TabLayout没有明确地提供向Tab中设置图标的途径，
// 但是很多事情总可以另辟蹊径。我们知道，Tab是使用adapter中的getPageTitle()方法做其显示的内容，
// 这个方法返回类型为CharSequence。于是，我们可以在PagerAdapter中重写getPageTitle()方法，
// 创建一个SpannableString，而将图标放置在ImageSpan中，设置在SpannableString中：
    //自定义实现tabitem的样式可以更改getPageTitle方法
//    @Override
//    public CharSequence getPageTitle(int position) {
//        Drawable drawable = ContextCompat.getDrawable(context,  imgs[position]);
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//        SpannableString sb = new SpannableString(titles[position]);
//        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
//        sb.setSpan(imageSpan, 0, titles[position].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return sb;
//    }
}
