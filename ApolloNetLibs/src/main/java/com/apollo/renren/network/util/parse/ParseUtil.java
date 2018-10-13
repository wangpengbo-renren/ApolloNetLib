package com.apollo.renren.network.util.parse;

/**
 * 解析工具类
 */
public class ParseUtil {

    private static IParseUtil INSTANCE;

    private ParseUtil() {
    }

    private static IParseUtil getParseUtil() {
        if (INSTANCE == null) {
            synchronized (ParseUtil.class) {
                if (INSTANCE == null) {
                    INSTANCE = new GsonUtil();
                }
            }
        }
        return INSTANCE;
    }


    public static <T> T parseJson(String json, Class<T> tClass) {
        return getParseUtil().parseJson(json, tClass);
    }


    public static <T> T parseInterfaceGenericJson(String json, Class clazz) {
        return getParseUtil().parseInterfaceGenericJson(json, clazz);
    }

    public static String toJson(Object object) {
        return getParseUtil().toJson(object);
    }
}
