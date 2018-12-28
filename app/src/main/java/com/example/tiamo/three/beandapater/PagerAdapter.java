package com.example.tiamo.three.beandapater;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tiamo.three.fragment.PingLFragment;
import com.example.tiamo.three.fragment.ShopFragment;
import com.example.tiamo.three.fragment.XiangQFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    private String[] name = new String[]{"商品","详情","评论"};

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch(i){
            case 0:
                return new ShopFragment();
            case 1:
                return new XiangQFragment();
            default:
                return new PingLFragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return name[position];
    }

    @Override
    public int getCount() {
        return name.length;
    }
}
