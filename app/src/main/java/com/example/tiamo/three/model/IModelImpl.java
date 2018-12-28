package com.example.tiamo.three.model;

import com.example.tiamo.three.RxManager;
import com.example.tiamo.three.okhttp.ICallBack;
import com.example.tiamo.three.okhttp.MyCallBack;
import com.example.tiamo.three.okhttp.OkHttpUtils;
import com.google.gson.Gson;

import java.util.Map;

public class IModelImpl implements IModel {
    @Override
    public void requestData(String path, Map<String, String> params, final Class clazz, final MyCallBack myCallBack) {
        /*OkHttpUtils.getInstance().postQueue(path, params, clazz, new ICallBack() {
            @Override
            public void failed(Exception e) {
                myCallBack.sucess(e);
            }

            @Override
            public void sucess(Object data) {
                myCallBack.sucess(data);
            }
        });*/

        RxManager.getRxManager().post(path,params).result(new RxManager.HttpListener() {
            @Override
            public void onSuccess(String data) {

                Object o = new Gson().fromJson(data, clazz);
                myCallBack.sucess(o);
            }

            @Override
            public void onFail(String error) {
                myCallBack.sucess(error);
            }
        });
    }
}
