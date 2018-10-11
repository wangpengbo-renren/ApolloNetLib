package com.apollo.renren.network.util;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

/**
 * 工具类
 */
public class UrlUtil {
    /**
     * 拼接get 请求url参数
     *
     * @param url
     * @param params
     * @return
     */
    public static String composeGetParams(@NonNull String url, ArrayMap<String, Object> params) {
        if (url == null) {
            throw new NullPointerException("url is null");
        }
        if (params == null || params.size() == 0) {
            return url;
        }
        StringBuilder paramUrl = new StringBuilder();
        if (!url.contains("?")) {
            paramUrl.append("?");
        }
        for (String key : params.keySet()) {

            if (params.get(key) == null) {
                continue; // the value is null, leave it.
            }
            paramUrl.append("&").append(params.get(key));
        }

        return url + paramUrl.toString();
    }
}
