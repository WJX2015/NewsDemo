package com.example.lenovo_g50_70.newsdemo.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lenovo_g50_70.newsdemo.R;
import com.example.lenovo_g50_70.newsdemo.adapter.VideoAdapter;
import com.example.lenovo_g50_70.newsdemo.bean.Video;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.AcFunHeader;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wjx on 2017/7/28.
 */

public class VideoFragment extends BaseFragment {

    private String videoUrl = "http://c.m.163.com/nc/video/list/V9LG4B3A0/y/0-20.html";
    private List<Video.V9LG4B3A0Bean> mResultBeen = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private VideoAdapter mVideoAdapter;
    private SpringView mSpringView;

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_video;
    }

    @Override
    public void initView() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler_video);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mVideoAdapter = new VideoAdapter(mResultBeen);
        mRecyclerView.setAdapter(mVideoAdapter);

        mSpringView = (SpringView) mView.findViewById(R.id.spring_video);
        mSpringView.setHeader(new AliHeader(getContext()));
        mSpringView.setFooter(new AliFooter(getContext()));
        mSpringView.setType(SpringView.Type.FOLLOW);
    }

    @Override
    public void initListener() {
        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {   //下拉刷新
                initData();
                mSpringView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {  //上拉加载
                initData();
                mSpringView.onFinishFreshAndLoad();
            }
        });
    }

    @Override
    public void initData() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, videoUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    String str = responseInfo.result;
                    Gson gson = new Gson();
                    Video video = gson.fromJson(str, Video.class);
                    mResultBeen = video.getV9LG4B3A0();
                    mHandler.sendEmptyMessage(1001);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1001:
                    mVideoAdapter.setVideoDatas(mResultBeen);
                    break;
            }
            return true;
        }
    });

}
