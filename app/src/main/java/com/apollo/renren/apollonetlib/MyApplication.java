package com.apollo.renren.apollonetlib;

import android.app.Application;

import com.apollo.renren.network.http.HttpBuilder;
import com.apollo.renren.network.http.HttpManager;
import com.apollo.renren.network.logger.AndroidLogAdapter;
import com.apollo.renren.network.logger.Logger;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化http
        HttpManager.initHttpManager(new HttpBuilder()
                .setCodeSuccess(200)//访问成功响应码
                .setConnectTimeOut(30 * 1000)
                .setReadTimeOut(30 * 1000)
                .setWriteTimeOut(30 * 1000));
        //初始化Logger
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
