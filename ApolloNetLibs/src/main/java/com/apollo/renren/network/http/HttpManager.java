package com.apollo.renren.network.http;

import com.apollo.renren.network.http.okhttp.OkHttpManager;

/**
 * 此类用于初始化http的一些设置，以及用于切换使用哪个http库，比如Okhttp和Retrofit等
 */
public final class HttpManager {
    private static IHttpManager mHttpManager;

    /**
     * 初始化HttpManager，最好在application启动时初始化一次
     *
     * @param httpBuilder
     */
    public static void initHttpManager(HttpBuilder httpBuilder) {
        mHttpManager = new OkHttpManager(httpBuilder);

//        mHttpManager = new RetrofigManager();
    }

    /**
     * 获取HttpManager实例
     *
     * @return
     */
   public static IHttpManager getHttpManager() {
        if (mHttpManager == null) {
            throw new RuntimeException("You have to invoke initHttpManager() to init HttpManager before you use it!");
        }

        return mHttpManager;
    }

    private HttpManager() {
        throw new UnsupportedOperationException("You can't instantiate this class but invoke getHttpManager() after called initHttpManager()!");
    }

}
