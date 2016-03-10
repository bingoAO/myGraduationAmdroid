package com.example.companyuo.customView;

import com.example.companyuo.LogUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;


/**
 * Created by sheshihao385 on 16/1/15.
 */
public class BlurBackGround extends LinearLayout implements View.OnClickListener{


    public BlurBackGround(Context context) {
        super(context);
    }

    public BlurBackGround(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("NewApi") 
    public BlurBackGround(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public void onClick(View v) {
        changeBackground();
    }

    private void changeBackground() {
        LogUtils.d("changeBackGround");
    }
}
