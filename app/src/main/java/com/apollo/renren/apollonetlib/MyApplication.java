package com.apollo.renren.apollonetlib;

import android.app.Application;

import com.apollo.renren.network.http.NetworkType;
import com.apollo.renren.network.util.ApolloHttpUtil;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ApolloHttpUtil.getInstance().init(NetworkType.OKHTTP);
    }
}
