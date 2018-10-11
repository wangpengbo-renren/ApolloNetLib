package com.apollo.renren.network.response;

import android.support.annotation.Nullable;

/**
 * 响应体
 * @param <T>
 */
public class Response<T> {
    //响应码
    private int code;
    //响应消息
    private String msg;
    //响应数据
    @Nullable
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Nullable
    public T getData() {
        return data;
    }

    public void setData(@Nullable T data) {
        this.data = data;
    }
}
