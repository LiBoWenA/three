package com.example.tiamo.three;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;

import com.example.tiamo.three.bean.Bean;
import com.example.tiamo.three.bean.Chuandi;
import com.example.tiamo.three.beandapater.BeanAdapter;
import com.example.tiamo.three.persenter.IPersenterImpl;
import com.example.tiamo.three.view.HeadView;
import com.example.tiamo.three.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView {
    private String path = "searchProducts";
    private IPersenterImpl iPersenter;
    private XRecyclerView xRecyclerView;
    HeadView headView;
    private BeanAdapter adapter;
    private int page;
    private boolean isLinear = true;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page = 1;
        init();
        EventBus.getDefault().register(this);
    }

    private void init() {
        iPersenter = new IPersenterImpl(this);
        //获取资源ID
        xRecyclerView = findViewById(R.id.xview);
        headView = findViewById(R.id.headview);
        //点击头部进行切换
        headView.setOnClick(new HeadView.OnClick() {
            @Override
            public void Click() {
                List<Bean.DataBean> data = adapter.getData();
                changeLiGr();
                adapter.setData(data);
            }
        });
        headView.setOnItemClick(new HeadView.OnItemsClick() {
            @Override
            public void Click(String s) {
                page=1;
                initData(s,page);
            }
        });
        //刷新加载
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setPullRefreshEnabled(true);
        changeLiGr();
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                initData(name,page);
            }

            @Override
            public void onLoadMore() {
                initData(name,page);
            }
        });
        //发送请求
        xRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //点击跳转



    }

    private void initData(String name,int page) {
        Map<String,String> pamars = new HashMap<>();
        pamars.put("keywords",name);
        pamars.put("page",page+"");
        iPersenter.showRequestData(path,pamars,Bean.class);
    }

    private void changeLiGr(){
        if (isLinear){
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            xRecyclerView.setLayoutManager(layoutManager);
        }else{
            GridLayoutManager manager = new GridLayoutManager(this,2);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            xRecyclerView.setLayoutManager(manager);
        }
        adapter = new BeanAdapter(this,isLinear);
        xRecyclerView.setAdapter(adapter);
        adapter.setOnClick(new BeanAdapter.OnClick() {
            @Override
            public void click(int pid) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                intent.putExtra("pid",pid+"");
                startActivity(intent);
            }
        });
        //状态反选
        isLinear = !isLinear;
    }


    @Override
    public void startRequestData(Object data) {
        if (data instanceof Bean){
            Bean bean = (Bean) data;
            if (page == 1){
                adapter.setData(bean.getData());
            }else{
                adapter.addData(bean.getData());
            }
            page++;
            xRecyclerView.refreshComplete();
            xRecyclerView.loadMoreComplete();;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getName(Chuandi chuandi){
        if (chuandi.getFlag().equals("shop_name")) {
            initData(chuandi.getData().toString(), page);
        }
    }
    //解绑
    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPersenter.onDestory();
        EventBus.getDefault().unregister(this);
    }
}
