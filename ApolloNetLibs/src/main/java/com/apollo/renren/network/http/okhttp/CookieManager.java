package com.apollo.renren.network.http.okhttp;

import android.support.v4.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * cookie管理器
 */
public class CookieManager implements CookieJar {
    private static CookieManager INSTANCE = null;
    private ArrayMap<HttpUrl, List<Cookie>> mCookieArray;

    private CookieManager() {
        mCookieArray = new ArrayMap<>();
    }

    static CookieManager getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (CookieManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CookieManager();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        mCookieArray.put(url, cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookieList = mCookieArray.get(url);
        if (cookieList != null) {
            return cookieList;
        }
        return new ArrayList<>();
    }
}
