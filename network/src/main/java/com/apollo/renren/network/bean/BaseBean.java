package com.apollo.renren.network.bean;

public class BaseBean<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "data=" + data +
                '}';
    }
}
