package com.apollo.renren.network.thread;

import android.support.annotation.Nullable;

public interface OnErrorListener<T extends Throwable> {
    /**
     * 错误回调
     * @param t
     */
    void onError(@Nullable T t);
}
