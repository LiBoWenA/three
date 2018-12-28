package com.example.tiamo.three.bean;

public class Chuandi {
    private Object data;
    private String flag;

    public Chuandi(Object data, String flag) {
        this.data = data;
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
