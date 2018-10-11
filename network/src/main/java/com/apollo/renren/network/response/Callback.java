package com.apollo.renren.network.response;

/**
 * 网络请求回调
 * @param <T>
 */
public interface Callback<T> {
    /**
     * 成功响应
     * @param bean
     * @param response
     */
    void onSuccess(T bean,Response<T> response);

    /**
     * 失败响应
     * @param errorCode
     * @param errorMsg
     * @param response
     */
    void onFailure(int errorCode,String errorMsg,Response<T> response);
}
