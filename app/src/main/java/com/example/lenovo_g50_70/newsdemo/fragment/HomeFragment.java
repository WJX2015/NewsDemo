package com.example.lenovo_g50_70.newsdemo.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.lenovo_g50_70.newsdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wjx on 2017/7/28.
 */

public class HomeFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private String[] mTitles = {"国内", "头条", "时尚", "娱乐", "财经", "体育", "社会", "科技", "军事", "国际"};
    private String[] mChannels = {
            "http://v.juhe.cn/toutiao/index?type=guonei&key=a2a7a2c0c7968be77dae6f6fc73a484a",
            "http://v.juhe.cn/toutiao/index?type=top&key=a2a7a2c0c7968be77dae6f6fc73a484a",
            "http://v.juhe.cn/toutiao/index?type=shishang&key=a2a7a2c0c7968be77dae6f6fc73a484a",
            "http://v.juhe.cn/toutiao/index?type=yule&key=a2a7a2c0c7968be77dae6f6fc73a484a",
            "http://v.juhe.cn/toutiao/index?type=caijing&key=a2a7a2c0c7968be77dae6f6fc73a484a",
            "http://v.juhe.cn/toutiao/index?type=tiyu&key=a2a7a2c0c7968be77dae6f6fc73a484a",
            "http://v.juhe.cn/toutiao/index?type=shehui&key=a2a7a2c0c7968be77dae6f6fc73a484a",
            "http://v.juhe.cn/toutiao/index?type=keji&key=a2a7a2c0c7968be77dae6f6fc73a484a",
            "http://v.juhe.cn/toutiao/index?type=junshi&key=a2a7a2c0c7968be77dae6f6fc73a484a",
            "http://v.juhe.cn/toutiao/index?type=guoji&key=a2a7a2c0c7968be77dae6f6fc73a484a"};

    private List<NewsFragment> mNewsFragments = new ArrayList<>();

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        mTabLayout = (TabLayout) mView.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) mView.findViewById(R.id.view_pager_home);

        //联动绑定
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mNewsFragments.get(position);
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });
    }

    @Override
    public void initListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void initData() {
        for (int i = 0; i < mTitles.length; i++) {
            NewsFragment newsFragment = new NewsFragment();
            newsFragment.setChannels(mChannels[i]);
            newsFragment.setNewsType(i);
            mNewsFragments.add(newsFragment);
        }
    }
}
