package com.example.tiamo.three.model;


import com.example.tiamo.three.okhttp.MyCallBack;

import java.util.Map;

public interface IModel {
    void requestData(String path, Map<String, String> params, Class clazz, MyCallBack myCallBack);
}
