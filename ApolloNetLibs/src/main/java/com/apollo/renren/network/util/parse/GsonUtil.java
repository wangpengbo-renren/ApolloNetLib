package com.apollo.renren.network.util.parse;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;

public class GsonUtil implements IParseUtil {
    private static Gson mGson = new Gson();

    /**
     * json转为bean
     *
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T convertJson2Bean(String json, Class<T> tClass) {
        return mGson.fromJson(json, tClass);
    }

    /**
     * object对象转json
     *
     * @param object
     * @return
     */
    public static String convertObject2Json(Object object) {
        return mGson.toJson(object);
    }

    @Override
    public <T> T parseJson(String json, Class<T> clazz) {
        return convertJson2Bean(json, clazz);
    }

    @Override
    public <T> T parseInterfaceGenericJson(String json, Class clazz) {
        //接口泛型使用clazz.getGenericInterfaces()
        //抽象类泛型使用clazz.getGenericSuperclass();
        return mGson.fromJson(json, (((ParameterizedType)
                (clazz.getGenericInterfaces())[0])
                .getActualTypeArguments()[0]));
    }

    @Override
    public String toJson(Object object) {
        return convertObject2Json(object);
    }
}
