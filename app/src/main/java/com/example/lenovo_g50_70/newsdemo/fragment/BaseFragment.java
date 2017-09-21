package com.example.lenovo_g50_70.newsdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by wjx on 2017/7/28.
 */

public abstract class BaseFragment extends Fragment {

    protected View mView;
    private Toast mToast;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(getLayoutResourceId(), container, false);
            initView();
            initListener();
            initData();
        }
        return mView;
    }

    /**
     * 界面的布局ID
     */
    public abstract int getLayoutResourceId();

    /**
     * 控件的绑定
     */
    public abstract void initView();

    /**
     * 监听器的设置
     */
    public abstract void initListener();

    /**
     * 数据的填充
     */
    public abstract void initData();

    public void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT);
        }
        //直接刷新显示内容
        mToast.setText(msg);
        mToast.show();
    }
}
