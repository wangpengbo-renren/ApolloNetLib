package com.apollo.renren.network.http;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import com.apollo.renren.network.response.Callback;
import com.apollo.renren.network.response.DownLoadListener;
import com.apollo.renren.network.response.UploadListener;

/**
 * 该接口用于定义网络框架的基本协议，如Get、Post请求
 * 不管用什么第三方网络库，封装第三方库的类都要实现这个接口
 * 如使用Okhttp，则创建OkhttpManager，实现IHttpManager
 * 如使用Retrofit，则创建RetrofitHttpManager，实现IHttpManager
 */
public interface IHttpManager {
    <T> void doGet(@NonNull String tag, @NonNull String url, @Nullable ArrayMap<String, Object> params, @Nullable Callback<T> callback);

    <T> void doPost(@NonNull String tag, @NonNull String url, @Nullable ArrayMap<String, Object> params, @Nullable Callback<T> callback);

    <T> void doDelete(@NonNull String tag, @NonNull String url, @Nullable ArrayMap<String, Object> params, @Nullable Callback<T> callback);

    <T> void doPut(@NonNull String tag, @NonNull String url, @Nullable ArrayMap<String, Object> params, @Nullable Callback<T> callback);

    <T> void doPatch(@NonNull String tag, @NonNull String url, @Nullable ArrayMap<String, Object> params, @Nullable Callback<T> callback);

    void doDownLoadFile(@NonNull String tag, @NonNull String url, @NonNull String savePath, @Nullable DownLoadListener downLoadListener);

    void doUploadFile(@NonNull String tag, @NonNull String url, @NonNull String filePath, @Nullable UploadListener uploadListener);

    void cancelRequestByTag(Object tag);

    void cacelAllRequests();
}
