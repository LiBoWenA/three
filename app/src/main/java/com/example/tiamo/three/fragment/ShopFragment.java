package com.example.tiamo.three.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiamo.three.R;
import com.example.tiamo.three.bean.Chuandi;
import com.example.tiamo.three.bean.GoodsBean;
import com.example.tiamo.three.persenter.IPersenterImpl;
import com.example.tiamo.three.view.IView;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopFragment extends Fragment implements IView {

    private IPersenterImpl iPersenter;
    private String path = "getProductDetail";
    private int data;

    @BindView(R.id.img)
    SimpleDraweeView imageView;
    @BindView(R.id.t_text)
    TextView tTitle;
    @BindView(R.id.t_price)
    TextView tPrice;
    @BindView(R.id.xiangq)
    Button bQx;
    @BindView(R.id.pingl)
    Button bPl;
    private GoodsBean.DataBean data1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.shopfragment,null);

        ButterKnife.bind(this,view);
        iPersenter = new IPersenterImpl(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent = getActivity().getIntent();
        String pid = intent.getStringExtra("pid");

        Map<String,String> map = new HashMap<>();
        map.put("pid",pid);
        iPersenter.showRequestData(path,map,GoodsBean.class);
    }

    @OnClick({R.id.xiangq,R.id.pingl})
    public void setClick(View v){
        switch (v.getId()){
            case R.id.xiangq:
                EventBus.getDefault().post(new Chuandi(data1.getTitle()+"","XQname"));
                break;

            case R.id.pingl:
                Log.i("TAG","!!!!!!!!!!!!!!!!!!!!!"+data1.getPrice());
                EventBus.getDefault().postSticky(new Chuandi(data1.getPrice()+"","pppp"));
                break;
                default:
                    break;
        }
    }

    @Override
    public void startRequestData(Object data) {
        if (data instanceof GoodsBean){
            GoodsBean goodsBean = (GoodsBean) data;
            data1 = goodsBean.getData();
            String images = data1.getImages();
            String[] split = images.split("\\|");
            Uri uri = Uri.parse(split[0]);
            imageView.setImageURI(uri);

            tTitle.setText(data1.getTitle());
            tPrice.setText(data1.getPrice()+"");



        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iPersenter.onDestory();
       // EventBus.getDefault().unregister(this);
    }
}
