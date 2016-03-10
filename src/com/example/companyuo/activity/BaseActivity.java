package com.example.companyuo.activity;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by sheshihao385 on 16/1/15.
 */
public abstract class BaseActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDatas();
        findViews();
        setUpListeners();
        setUpViews();
    }

    public abstract void initDatas();
    public abstract void findViews();
    public abstract void setUpListeners();
    public abstract void setUpViews();

}
