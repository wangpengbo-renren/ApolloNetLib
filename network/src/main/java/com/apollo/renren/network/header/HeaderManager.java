package com.apollo.renren.network.header;

import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;

import com.apollo.renren.network.logger.Logger;

public class HeaderManager implements IHeaderManager {

    private static HeaderManager INSTANCE = null;
    //将null值转为""
    private boolean mTurnNullValueToEmpty = true;
    private ArrayMap<String, String> mHeaders = new ArrayMap<>();


    private HeaderManager() {
    }

    public static HeaderManager getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (HeaderManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HeaderManager();
                }
            }
        }

        return INSTANCE;
    }

    @Override
    public ArrayMap<String, String> getHeaders() {
        return mHeaders;
    }

    @Override
    public boolean addHeader(String headerName, String headerValue) {
        if (headerName == null) {
            throw new NullPointerException("name == null");
        }
        if (headerName.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        }
        if (mTurnNullValueToEmpty && headerValue == null) {
            headerValue = "";
        }
        mHeaders.put(headerName, headerValue);

        return true;
    }

    @Override
    public boolean addHeaders(ArrayMap<String, String> params) {
        try {
            mHeaders.putAll((SimpleArrayMap<? extends String, ? extends String>) params);
        } catch (Exception e) {
            Logger.e("Add headers failed = " + e.toString());
            return false;
        }
        return true;
    }

    @Override
    public boolean removeHeader(@Nullable String headerName) {
        mHeaders.remove(headerName);
        return true;
    }

    @Override
    public void clearHeaders() {
        mHeaders.clear();
    }

    @Override
    public String getHeader(@Nullable String headerName) {
        if (headerName == null)
            return null;

        return mHeaders.get(headerName);
    }
}
