package com.example.lenovo_g50_70.newsdemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo_g50_70.newsdemo.R;
import com.example.lenovo_g50_70.newsdemo.activity.NewsDetailActivity;
import com.example.lenovo_g50_70.newsdemo.bean.News;
import com.example.lenovo_g50_70.newsdemo.holder.Holder01;
import com.example.lenovo_g50_70.newsdemo.holder.Holder02;
import com.example.lenovo_g50_70.newsdemo.holder.Holder03;

import java.util.List;

/**
 * Created by wjx on 2017/7/28.
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<News.ResultBean.DataBean> mResultBeen;

    public NewsAdapter(List<News.ResultBean.DataBean> resultBeen) {
        mResultBeen = resultBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        switch (viewType) {
            case 1:
                View view01 = LayoutInflater.from(context).inflate(R.layout.item_news_one, null);
                final Holder01 holder01 = new Holder01(view01);
                holder01.setOnItemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder01.getAdapterPosition();
                        News.ResultBean.DataBean news = mResultBeen.get(position);
                        Intent intent = new Intent(context, NewsDetailActivity.class);
                        intent.putExtra("URL", news.getUrl());
                        context.startActivity(intent);
                        ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                });
                holder01.mImageDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position =holder01.getLayoutPosition();
                        mResultBeen.remove(position);
                        notifyItemRemoved(position);
                    }
                });
                return holder01;
            case 2:
                View view02 = LayoutInflater.from(context).inflate(R.layout.item_news_two, null);
                final Holder02 holder02 = new Holder02(view02);
                holder02.setOnItemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder02.getAdapterPosition();
                        News.ResultBean.DataBean news = mResultBeen.get(position);
                        Intent intent = new Intent(context, NewsDetailActivity.class);
                        intent.putExtra("URL", news.getUrl());
                        context.startActivity(intent);
                        ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                });
                holder02.mImageDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position =holder02.getLayoutPosition();
                        mResultBeen.remove(position);
                        notifyItemRemoved(position);
                    }
                });
                return holder02;
            case 3:
                View view03 = LayoutInflater.from(context).inflate(R.layout.item_news_three, null);
                final Holder03 holder03 = new Holder03(view03);
                holder03.setOnItemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder03.getAdapterPosition();
                        News.ResultBean.DataBean news = mResultBeen.get(position);
                        Intent intent = new Intent(context, NewsDetailActivity.class);
                        intent.putExtra("URL", news.getUrl());
                        context.startActivity(intent);
                        ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                });
                holder03.mImageDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position =holder03.getLayoutPosition();
                        mResultBeen.remove(position);
                        notifyItemRemoved(position);
                    }
                });
                return holder03;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        News.ResultBean.DataBean news = mResultBeen.get(position);
        switch (type) {
            case 1:
                ((Holder01) holder).bindViewHolder(news);
                break;
            case 2:
                ((Holder02) holder).bindViewHolder(news);
                break;
            case 3:
                ((Holder03) holder).bindViewHolder(news);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mResultBeen.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mResultBeen.get(position).getThumbnail_pic_s03() != null) {
            return 3;
        } else if (mResultBeen.get(position).getThumbnail_pic_s02() != null) {
            return 2;
        } else {
            return 1;
        }
    }

    public void setNewsDatas(List<News.ResultBean.DataBean> resultBeen) {
        mResultBeen = resultBeen;
        notifyDataSetChanged();
    }

    public void addNewsDataInTop(List<News.ResultBean.DataBean> resultBeen) {
        mResultBeen.addAll(0, resultBeen);
        notifyDataSetChanged();
    }

    public void addNewsDataInBottom(List<News.ResultBean.DataBean> resultBeen) {
        mResultBeen.addAll(resultBeen);
        notifyDataSetChanged();
    }

}
