package com.example.musicdemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

//AppCompatImageView
public class WEqualsHImageView extends androidx.appcompat.widget.AppCompatImageView {
    public WEqualsHImageView(Context context) {
        super(context);
    }

    public WEqualsHImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WEqualsHImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
//        //获取View宽度
//        int width=MeasureSpec.getSize(widthMeasureSpec);
//        //获取View宽度
//        //match_parent,warp_content,具体dp
//        int mode=MeasureSpec.getMode(widthMeasureSpec);

    }
}
