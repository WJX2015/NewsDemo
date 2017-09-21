package com.example.lenovo_g50_70.newsdemo.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lenovo_g50_70.newsdemo.util.HttpUtil;
import com.example.lenovo_g50_70.newsdemo.R;
import com.example.lenovo_g50_70.newsdemo.adapter.NewsAdapter;
import com.example.lenovo_g50_70.newsdemo.bean.News;
import com.example.lenovo_g50_70.newsdemo.util.SPUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liaoinstan.springview.container.MeituanFooter;
import com.liaoinstan.springview.container.MeituanHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by wjx on 2017/7/28.
 */

public class NewsFragment extends BaseFragment {

    private SpringView mSpringView;
    private RecyclerView mRecyclerView;

    private List<News.ResultBean.DataBean> mResultBeen = new ArrayList<>();
    private NewsAdapter mAdapter;

    private String mNewsUrl;
    private int mNewsType;

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView() {
        mSpringView = (SpringView) mView.findViewById(R.id.spring_news);
        mSpringView.setHeader(new MeituanHeader(getContext()));
        mSpringView.setFooter(new MeituanFooter(getContext()));
        mSpringView.setType(SpringView.Type.FOLLOW);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler_news);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new NewsAdapter(mResultBeen);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {   //下拉刷新
                requestNewsDatas(1002);
                mSpringView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {  //上拉加载
                requestNewsDatas(1003);
                mSpringView.onFinishFreshAndLoad();
            }
        });
    }

    public void requestNewsDatas(final int what) {
        HttpUtil.sendOkHttpRequest(mNewsUrl, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //访问失败回调
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                new Thread(new Runnable() {
                    String mString = null;

                    @Override
                    public void run() {
                        try {
                            mString = response.body().string();
                            Gson gson = new Gson();
                            News newsData = gson.fromJson(mString, News.class);
                            mResultBeen = newsData.getResult().getData();
                            mHandler.sendEmptyMessage(what);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    @Override
    public void initData() {

        String str = SPUtil.getNewsDatas(getContext(), mNewsType, mNewsUrl);

        if (str == null) {
            requestNewsDatas(1001);
        } else {
            strToList(SPUtil.getNewsDatas(getContext(), mNewsType, mNewsUrl));
            mHandler.sendEmptyMessage(1001);
        }
    }

    private void strToList(String string) {
        Gson gson = new Gson();
        mResultBeen = gson.fromJson(string, new TypeToken<List<News.ResultBean.DataBean>>() {
        }.getType());
    }

    private String listToStr(List<News.ResultBean.DataBean> resultBeen) {
        Gson gson = new Gson();
        String str = gson.toJson(resultBeen);
        return str;
    }

    private void cacheNewsDatas() {
        SPUtil.cacheNewsDatas(getContext(), mNewsType, mNewsUrl, listToStr(mResultBeen));
    }

    public void setChannels(String url) {
        mNewsUrl = url;
    }

    public void setNewsType(int type) {
        mNewsType = type;
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1001:
                    mAdapter.setNewsDatas(mResultBeen);
                    break;
                case 1002:
                    mAdapter.addNewsDataInTop(mResultBeen);
                    mHandler.sendEmptyMessage(1004);
                    break;
                case 1003:
                    mAdapter.addNewsDataInBottom(mResultBeen);
                    break;
                case 1004:
                    cacheNewsDatas();
                    break;
            }
            return true;
        }
    });
}
