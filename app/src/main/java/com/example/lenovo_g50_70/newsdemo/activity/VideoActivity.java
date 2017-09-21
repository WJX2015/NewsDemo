package com.example.lenovo_g50_70.newsdemo.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.SeekBar;
import android.widget.VideoView;

import com.example.lenovo_g50_70.newsdemo.R;

public class VideoActivity extends BaseActivity {

    private VideoView mVideoView;
    private SeekBar mSeekBar;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_video;
    }

    @Override
    public void initView() {
        mVideoView = (VideoView) findViewById(R.id.video_view);
        mSeekBar = (SeekBar) findViewById(R.id.video_seekbar);

        initDatas();
    }

    private void initDatas() {
        Uri videoUri = getIntent().getData();
        if (videoUri != null) {
            mVideoView.setVideoURI(videoUri);
        }

    }

    @Override
    public void initListener() {

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mVideoView.start();
                mSeekBar.setVisibility(View.GONE);
                mSeekBar.setMax(mVideoView.getDuration());
                updateSeekbarProgress();
            }
        });

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (fromUser) {
                    mVideoView.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void updateSeekbarProgress() {
        mSeekBar.setProgress(mVideoView.getCurrentPosition());
        mHandler.sendEmptyMessageDelayed(1001, 300);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1001:
                    updateSeekbarProgress();
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
