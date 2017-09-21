package com.example.lenovo_g50_70.newsdemo.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lenovo_g50_70.newsdemo.R;
import com.example.lenovo_g50_70.newsdemo.adapter.JokesAdapter;
import com.example.lenovo_g50_70.newsdemo.bean.Jokes;
import com.example.lenovo_g50_70.newsdemo.util.HttpUtil;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by wjx on 2017/7/28.
 */

public class JokeFragment extends BaseFragment {

    private SpringView mSpringView;
    private RecyclerView mRecyclerView;
    private JokesAdapter mAdapter;

    private List<Jokes.ResultBean.DataBean> mDataBeen = new ArrayList<>();
    private int mJokesPage = 1;

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_joke;
    }

    @Override
    public void initView() {
        mSpringView = (SpringView) mView.findViewById(R.id.spring_joke);
        mSpringView.setHeader(new DefaultHeader(getContext()));
        mSpringView.setFooter(new DefaultFooter(getContext()));
        mSpringView.setType(SpringView.Type.FOLLOW);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler_joke);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new JokesAdapter(mDataBeen);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {   //下拉刷新
                refreshJokesData(true);
                mSpringView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {  //上拉加载
                refreshJokesData(false);
                mSpringView.onFinishFreshAndLoad();
            }
        });
    }

    private void refreshJokesData(final boolean b) {

        mJokesPage++;
        HttpUtil.sendOkHttpRequest(getJokesUrl(mJokesPage), new okhttp3.Callback() {
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
                            Jokes jokes = gson.fromJson(mString, Jokes.class);
                            mDataBeen = jokes.getResult().getData();
                            if (b) {
                                mHandler.sendEmptyMessage(1002);
                            } else {
                                mHandler.sendEmptyMessage(1003);
                            }
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
        HttpUtil.sendOkHttpRequest(getJokesUrl(mJokesPage), new okhttp3.Callback() {
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
                            Jokes jokes = gson.fromJson(mString, Jokes.class);
                            mDataBeen = jokes.getResult().getData();
                            mHandler.sendEmptyMessage(1001);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        HttpUtil.sendRequestWithHttpURLConnection("https://www.baidu.com", new HttpUtil.HttpUrlConnectCallback() {
            @Override
            public void onFinish(String response) {
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private String getJokesUrl(int page) {
        return "http://japi.juhe.cn/joke/content/list.from?" +
                "key=7eb409893cbccbdfbb42480aaf9ac143" +
                "&page=" + page + "&pagesize=20&sort=asc&time=1451577600";
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1001:
                    mAdapter.setJokesDatas(mDataBeen);
                    break;
                case 1002:
                    mAdapter.addJokesDataInTop(mDataBeen);
                    break;
                case 1003:
                    mAdapter.addJokesDataInBottom(mDataBeen);
                    break;
            }
            return true;
        }
    });
}
