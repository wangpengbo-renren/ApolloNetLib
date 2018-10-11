package com.apollo.renren.network.okhttp;

import android.util.ArrayMap;

import com.apollo.renren.network.http.IHttpManager;

import okhttp3.Headers;

/**
 * http头信息
 */
public interface IOkHttpManager extends IHttpManager {

    /**
     * 将原始headers转换为Okhttp的headers
     * @param originalHeaders 原始headers
     * @return Okhttp的headers
     */
    Headers getOkHttpHeaders(ArrayMap<String,String> originalHeaders);
}
