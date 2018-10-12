package com.apollo.renren.network.http.okhttp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import com.apollo.renren.network.constants.HttpCode;
import com.apollo.renren.network.header.HeaderManager;
import com.apollo.renren.network.header.IHeaderManager;
import com.apollo.renren.network.http.HttpBuilder;
import com.apollo.renren.network.http.LoggingInterceptor;
import com.apollo.renren.network.logger.Logger;
import com.apollo.renren.network.response.Callback;
import com.apollo.renren.network.thread.ThreadManager;
import com.apollo.renren.network.util.UrlUtil;
import com.apollo.renren.network.util.parse.ParseUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpManager implements IOkHttpManager {

    //最好单例
    private final OkHttpClient mOkHttpClient;
    //header管理
    private IHeaderManager mHeaderManager = HeaderManager.getINSTANCE();
    //缓存header，避免每次请求都创建header
    private static Headers mOkhttpHeaders;

    public OkHttpManager(HttpBuilder httpBuilder) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new LoggingInterceptor())
                .readTimeout(httpBuilder.getReadTimeOut(), TimeUnit.MILLISECONDS)
                .writeTimeout(httpBuilder.getWriteTimeOut(), TimeUnit.MILLISECONDS)
                .connectTimeout(httpBuilder.getConnectTimeOut(), TimeUnit.MILLISECONDS)
                .cookieJar(CookieManager.getINSTANCE());
        mOkHttpClient = builder.build();
    }

    @Override
    public Headers getOkHttpHeaders(ArrayMap<String, String> originalHeaders) {
        //避免每次请求都创建headers
        if (mOkhttpHeaders != null) {
            return mOkhttpHeaders;
        }
        Headers.Builder builder = new Headers.Builder();
        for (String headerName : originalHeaders.keySet()) {
            builder.set(headerName, originalHeaders.get(headerName));
        }
        mOkhttpHeaders = builder.build();
        return mOkhttpHeaders;
    }

    @Override
    public <T> void doGet(@NonNull String tag, @NonNull String url, @Nullable ArrayMap<String, Object> params, @Nullable Callback<T> callback) {
        //创建基本get请求
        final Request request = new Request.Builder()
                .tag(tag)
                .headers(getOkHttpHeaders(mHeaderManager.getHeaders()))
                .url(UrlUtil.composeGetParams(url, params))
                .build();
        handleNetTask(request, callback);
    }

    @Override
    public <T> void doPost(@NonNull String tag, @NonNull String url, @Nullable ArrayMap<String, Object> params, @Nullable Callback<T> callback) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), ParseUtil.toJson(params));
        Request request = new Request.Builder()
                .url(url)
                .tag(tag)
                .post(body)
                .build();
        handleNetTask(request, callback);
    }

    @Override
    public void cancelRequestByTag(Object tag) {
        if (mOkHttpClient != null && tag != null) {
            //队列中的请求
            for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
                if (call.request().tag().equals(tag)) {
                    call.cancel();
                }
            }
            //正在进行的请求
            for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
                if (call.request().tag().equals(tag)) {
                    call.cancel();
                }
            }
        }
    }

    @Override
    public void cacelAllRequests() {
        if (mOkHttpClient != null) {
            for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
                call.cancel();
            }
            for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
                call.cancel();
            }
        }
    }

    /**
     * 执行网络请求
     *
     * @param request          request对象
     * @param responseCallback 请求结果回调
     * @param <T>              泛型
     */
    private <T> void handleNetTask(Request request, final Callback<T> responseCallback) {
        mOkHttpClient.newCall(request)
                .enqueue(new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        handleFailure(e, responseCallback);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        handleResponse(response, responseCallback);
                    }
                });
    }

    /**
     * 处理失败
     *
     * @param e
     * @param responseCallback
     * @param <T>
     */
    private <T> void handleFailure(IOException e, Callback<T> responseCallback) {
        com.apollo.renren.network.response.Response<T> failureResponse = new com.apollo.renren.network.response.Response<>();
        failureResponse.setMsg(e.toString());
        onCallBack(failureResponse, responseCallback);
    }

    /**
     * 处理响应结果
     *
     * @param response
     * @param responseCallback
     * @param <T>
     */
    private <T> void handleResponse(Response response, Callback<T> responseCallback) {
        com.apollo.renren.network.response.Response<T> result = new com.apollo.renren.network.response.Response();

        try {
            if (responseCallback != null) {
                if (response.body() != null) {
                    String json = response.body().string();
                    Logger.i(json);
                    //解析bean
                    // TODO: 2018/10/11 若是jsonarray？
                    T bean = ParseUtil.parseInterfaceGenericJson(json, responseCallback.getClass());
                    result.setData(bean);
                }
                //触发回调
                onCallBack(result, responseCallback);
            }
        } catch (Exception e) {
            Logger.e("Parsing json failed: " + e.toString());
            com.apollo.renren.network.response.Response<T> failureResponse = new com.apollo.renren.network.response.Response<>();
            onCallBack(failureResponse, responseCallback);
        }
    }

    /**
     * 执行回调
     *
     * @param response
     * @param responseCallback
     * @param <T>
     */
    private <T> void onCallBack(com.apollo.renren.network.response.Response<T> response, Callback<T> responseCallback) {
        ThreadManager.runOnUIThread(() -> {
            if (responseCallback != null) {
                if (response.getCode() == HttpCode.CODE_SUCCESS) {
                    responseCallback.onSuccess(response.getData(), response);
                } else {
                    responseCallback.onFailure(response.getCode(), response.getMsg(), response);
                }
            }
        });
    }
}
