package com.apollo.renren.network.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import com.apollo.renren.network.http.HttpManager;
import com.apollo.renren.network.http.IHttpManager;
import com.apollo.renren.network.response.Callback;

public abstract class BaseHttpUtil implements IHttpManager {
    private IHttpManager mHttpManager = HttpManager.getHttpManager();

    /**
     * Add algorithms of sign.
     *
     * @param params params the original requesting params.
     * @return The params with sign or the original params if you don't need to sign the params.
     */
    protected abstract ArrayMap<String, Object> getSignParams(ArrayMap<String, Object> params);

    @Override
    public <T> void doGet(@NonNull String tag, @NonNull String url, @Nullable ArrayMap<String, Object> params, @Nullable Callback<T> callback) {
        mHttpManager.doGet(tag, url, params, callback);
    }

    @Override
    public <T> void doPost(@NonNull String tag, @NonNull String url, @Nullable ArrayMap<String, Object> params, @Nullable Callback<T> callback) {
        mHttpManager.doPost(tag, url, params, callback);
    }

    /**
     * 使用url作为tag
     *
     * @param url      请求地址
     * @param params   参数
     * @param callback 回调
     * @param <T>      泛型
     */
    public <T> void doGet(@NonNull String url, @Nullable ArrayMap<String, Object> params, @Nullable Callback<T> callback) {
        doGet(url, url, params, callback);
    }

    /**
     * 使用url作为tag
     *
     * @param url
     * @param params
     * @param callback
     * @param <T>
     */
    public <T> void doPost(@NonNull String url, @Nullable ArrayMap<String, Object> params, @Nullable Callback<T> callback) {
        doPost(url, url, params, callback);
    }

    @Override
    public void cancelRequestByTag(Object tag) {
        mHttpManager.cancelRequestByTag(tag);
    }

    @Override
    public void cacelAllRequests() {
        mHttpManager.cacelAllRequests();
    }
}
