package com.example.tiamo.three;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.tiamo.three.bean.Chuandi;
import com.example.tiamo.three.bean.GoodsBean;
import com.example.tiamo.three.beandapater.PagerAdapter;
import com.example.tiamo.three.fragment.PingLFragment;
import com.example.tiamo.three.fragment.ShopFragment;
import com.example.tiamo.three.fragment.XiangQFragment;
import com.example.tiamo.three.persenter.IPersenterImpl;
import com.example.tiamo.three.view.IView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends AppCompatActivity{


    @BindView(R.id.group)
    TabLayout group;
    @BindView(R.id.pager)
    ViewPager pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");

        /*pager.setOffscreenPageLimit(3);*/
        pager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        group.setupWithViewPager(pager);
    }

}
