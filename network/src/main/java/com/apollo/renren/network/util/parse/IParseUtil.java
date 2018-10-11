package com.apollo.renren.network.util.parse;

public interface IParseUtil {
    /**
     * json转为bean
     *
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    <T> T parseJson(String json, Class<T> tClass);

    /**
     * json转为bean，T 为 Interface generic type.
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T parseInterfaceGenericJson(String json, Class clazz);

    /**
     * bean转为json
     *
     * @param object
     * @return
     */
    String toJson(Object object);
}
