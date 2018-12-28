package com.example.tiamo.three.persenter;

import com.example.tiamo.three.model.IModel;
import com.example.tiamo.three.model.IModelImpl;
import com.example.tiamo.three.okhttp.MyCallBack;
import com.example.tiamo.three.view.IView;

import java.util.Map;

public class IPersenterImpl implements IPersenter {
    private IView iView;
    private IModel iModel;

    public IPersenterImpl(IView iView) {
        this.iView = iView;
        iModel = new IModelImpl();
    }


    @Override
    public void showRequestData(String path, Map<String, String> pamars, Class clazz) {
        iModel.requestData(path, pamars, clazz, new MyCallBack() {
            @Override
            public void sucess(Object data) {
                iView.startRequestData(data);
            }
        });
    }

    public void onDestory(){
        if (iModel != null){
            iModel = null;
        }
        if (iView != null){
            iView = null;
        }
    }
}
