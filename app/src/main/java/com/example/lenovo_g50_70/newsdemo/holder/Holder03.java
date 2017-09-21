package com.example.lenovo_g50_70.newsdemo.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo_g50_70.newsdemo.R;
import com.example.lenovo_g50_70.newsdemo.bean.News;

/**
 * Created by wjx on 2017/7/29.
 */

public class Holder03 extends RecyclerView.ViewHolder {

    public View mLayoutView;
    public ImageView mImageView01;
    public ImageView mImageView02;
    public ImageView mImageView03;
    public ImageView mImageDelete;
    public TextView mTitle;
    public TextView mAuthor;
    public TextView mDate;

    public Holder03(View itemView) {
        super(itemView);
        mLayoutView = itemView;
        mTitle = (TextView) mLayoutView.findViewById(R.id.news_title);
        mAuthor = (TextView) mLayoutView.findViewById(R.id.news_author);
        mDate = (TextView) mLayoutView.findViewById(R.id.news_date);
        mImageView01 = (ImageView) mLayoutView.findViewById(R.id.news_image_01);
        mImageView02 = (ImageView) mLayoutView.findViewById(R.id.news_image_02);
        mImageView03 = (ImageView) mLayoutView.findViewById(R.id.news_image_03);
        mImageDelete = (ImageView) mLayoutView.findViewById(R.id.news_delete);

    }

    /**
     * 绑定viewHolder
     *
     * @param news
     */
    public void bindViewHolder(News.ResultBean.DataBean news) {
        mTitle.setText(news.getTitle());
        mAuthor.setText(news.getAuthor_name());
        mDate.setText(news.getDate());

        Glide.with(mImageView01.getContext())
                .load(news.getThumbnail_pic_s())
                .into(mImageView01);
        Glide.with(mImageView02.getContext())
                .load(news.getThumbnail_pic_s02())
                .into(mImageView02);
        Glide.with(mImageView03.getContext())
                .load(news.getThumbnail_pic_s03())
                .into(mImageView03);
    }

    /**
     * 设置Item点击事件
     *
     * @param listener
     */
    public void setOnItemClickListener(View.OnClickListener listener) {
        mLayoutView.setOnClickListener(listener);
    }
}