package com.apollo.renren.network.header;

import android.support.annotation.Nullable;
import android.util.ArrayMap;

public interface IHeaderManager {
    ArrayMap<String, String> getHeaders();

    boolean addHeader(String headerName, String headerValue);

    boolean addHeaders(ArrayMap<String, String> params);

    boolean removeHeader(@Nullable String headerName);

    void clearHeaders();

    String getHeader(@Nullable String headerName);
}
