package com.diyview.entity;

import android.support.annotation.NonNull;

/**
 * Created by Right on 2017/3/27.
 */

public class PieData {
    /**
     * 用户关心数据
     * */
    private String name;//名称
    private float value;//数值
    private float percentage;//百分比


    private int color;//颜色
    private float angle;//角度

    public PieData(@NonNull String name,@NonNull float value){
        this.name=name;
        this.value=value;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
