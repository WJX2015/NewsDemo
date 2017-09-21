package com.example.lenovo_g50_70.newsdemo.activity;

import android.animation.Animator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lenovo_g50_70.newsdemo.R;

public class GuideActivity extends BaseActivity {

    private ImageView mImageView;
    private Button mButton;

    private int imageIndex = 0;
    private int[] mImages = new int[]{
            R.drawable.guide_image01,
            R.drawable.guide_image02,
            R.drawable.guide_image03,
            R.drawable.guide_image04,
            R.drawable.guide_image05,
            R.drawable.guide_image06,
            R.drawable.guide_image07
    };

    private boolean mExitActivity = false;

    //音乐播放
    private MediaPlayer mMediaPlayer;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {
        mImageView = (ImageView) findViewById(R.id.guide_imageview);
        mButton = (Button) findViewById(R.id.guide_button);

        startImageAnim();
        playMusic();
    }

    @Override
    public void initListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                stopMusic();
                finish();
            }
        });
    }

    /**
     * 执行图片的动画
     */
    public void startImageAnim() {
        //取出当前背景图片
        int imageId = mImages[imageIndex % mImages.length];
        mImageView.setBackgroundResource(imageId);
        imageIndex++;

        //重置图片大小
        mImageView.setScaleX(1f);
        mImageView.setScaleY(1f);

        //执行动画
        mImageView.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(3000)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        //动画开始回调
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //动画结束回调
                        if (!mExitActivity) {
                            startImageAnim();
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        //动画取消回调
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        //动画重复回调
                    }
                }).start();
    }

    /**
     * 音乐播放
     */
    private void playMusic() {
        try {
            //创建一个播放器
            mMediaPlayer = MediaPlayer.create(this, R.raw.new_version);
            //设置循环播放
            mMediaPlayer.setLooping(true);
            //设置左声道右声道的音量大小,0-1,float
            mMediaPlayer.setVolume(1f, 1f);
            //开始播放
            mMediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 音乐停止播放
     */
    public void stopMusic() {
        try {
            if (mMediaPlayer != null) {
                mMediaPlayer.stop();
                mMediaPlayer.release();
                mMediaPlayer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mExitActivity = true;
        stopMusic();
    }
}
