package com.apollo.renren.network.util;

import android.support.v4.util.ArrayMap;

import com.apollo.renren.network.http.HttpBuilder;
import com.apollo.renren.network.http.HttpManager;
import com.apollo.renren.network.http.NetworkType;
import com.apollo.renren.network.logger.AndroidLogAdapter;
import com.apollo.renren.network.logger.Logger;

/**
 * 最终使用的工具类，直接拿来访问网络
 */
public class ApolloHttpUtil extends BaseHttpUtil {
    private static ApolloHttpUtil INSTANCE = null;

    private ApolloHttpUtil() {
    }

    public static ApolloHttpUtil getInstance() {
        if (INSTANCE == null) {
            synchronized (ApolloHttpUtil.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ApolloHttpUtil();
                }
            }
        }
        return INSTANCE;
    }

    public void init(NetworkType type) {
        //初始化http
        HttpManager.initHttpManager(type, new HttpBuilder()
                .setCodeSuccess(200)//访问成功响应码
                .setConnectTimeOut(30 * 1000)
                .setReadTimeOut(30 * 1000)
                .setWriteTimeOut(30 * 1000));
        refreshHttpManager();
        //初始化Logger
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    @Override
    protected ArrayMap<String, Object> getSignParams(ArrayMap<String, Object> params) {
        return params;
    }
}
