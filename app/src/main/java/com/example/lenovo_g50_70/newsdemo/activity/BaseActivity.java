package com.example.lenovo_g50_70.newsdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wjx on 2017/7/28.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());

        initView();
        initListener();
    }

    public abstract int getLayoutResourceId();

    public abstract void initView();

    public abstract void initListener();
}
