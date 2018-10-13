package com.apollo.renren.network.util;

import android.support.v4.util.ArrayMap;

/**
 * 最终使用的工具类，直接拿来访问网络
 */
public class ApolloHttpUtil extends BaseHttpUtil {
    private static ApolloHttpUtil INSTANCE = null;

    private ApolloHttpUtil() {
    }

    public static ApolloHttpUtil getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (ApolloHttpUtil.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ApolloHttpUtil();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    protected ArrayMap<String, Object> getSignParams(ArrayMap<String, Object> params) {
        return params;
    }
}
