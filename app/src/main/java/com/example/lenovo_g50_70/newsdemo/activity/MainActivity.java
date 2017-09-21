package com.example.lenovo_g50_70.newsdemo.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.example.lenovo_g50_70.newsdemo.R;
import com.example.lenovo_g50_70.newsdemo.fragment.HomeFragment;
import com.example.lenovo_g50_70.newsdemo.fragment.JokeFragment;
import com.example.lenovo_g50_70.newsdemo.fragment.MineFragment;
import com.example.lenovo_g50_70.newsdemo.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class MainActivity extends BaseActivity {

    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;

    private List<Fragment> mFragments =new ArrayList<>();

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);

        mFragments.add(new HomeFragment());
        mFragments.add(new VideoFragment());
        mFragments.add(new JokeFragment());
        mFragments.add(new MineFragment());
    }

    @Override
    public void initListener() {
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mRadioGroup.check(R.id.radio_01);
                        break;
                    case 1:
                        mRadioGroup.check(R.id.radio_02);
                        break;
                    case 2:
                        mRadioGroup.check(R.id.radio_03);
                        break;
                    case 3:
                        mRadioGroup.check(R.id.radio_04);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radio_01:
                        mViewPager.setCurrentItem(0,false);
                        break;
                    case R.id.radio_02:
                        mViewPager.setCurrentItem(1,false);
                        break;
                    case R.id.radio_03:
                        mViewPager.setCurrentItem(2,false);
                        break;
                    case R.id.radio_04:
                        mViewPager.setCurrentItem(3,false);
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JCVideoPlayer.releaseAllVideos();
    }
}
