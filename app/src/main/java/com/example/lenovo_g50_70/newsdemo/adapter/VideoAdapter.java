package com.example.lenovo_g50_70.newsdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo_g50_70.newsdemo.R;
import com.example.lenovo_g50_70.newsdemo.bean.Video;

import java.util.ArrayList;
import java.util.List;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by wjx on 2017/7/31.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {

    private List<Video.V9LG4B3A0Bean> mResultBeen = new ArrayList<>();

    public VideoAdapter(List<Video.V9LG4B3A0Bean> resultBeen) {
        mResultBeen = resultBeen;
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, null);
        VideoHolder videoHolder = new VideoHolder(view);
        return videoHolder;
    }

    @Override
    public void onBindViewHolder(VideoHolder holder, int position) {
        Video.V9LG4B3A0Bean resultBean = mResultBeen.get(position);

        holder.mJCVideoPlayerStandard.setUp(resultBean.getMp4_url(),
                JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL ,resultBean.getTitle());

        holder.mJCVideoPlayerStandard.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(holder.mJCVideoPlayerStandard.thumbImageView.getContext())
                .load(resultBean.getCover())
                .into(holder.mJCVideoPlayerStandard.thumbImageView);

        holder.mVideoTitle.setText(resultBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return (mResultBeen == null) ? 0 : mResultBeen.size();
    }

    static class VideoHolder extends RecyclerView.ViewHolder {

        JCVideoPlayerStandard mJCVideoPlayerStandard;
        TextView mVideoTitle;
        TextView mVideoDuration;

        public VideoHolder(View itemView) {
            super(itemView);

            mJCVideoPlayerStandard = (JCVideoPlayerStandard) itemView.findViewById(R.id.video_player);
            mVideoTitle = (TextView) itemView.findViewById(R.id.video_title);
        }
    }

    public void setVideoDatas(List<Video.V9LG4B3A0Bean> resultBeen) {
        mResultBeen = resultBeen;
        notifyDataSetChanged();
    }
}
