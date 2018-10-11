package com.apollo.renren.network.thread;

import android.support.annotation.Nullable;

public interface ObserverListener<T> {
    /**
     * 运行在UI线程
     *
     * @param t
     */
    void runOnUIThread(@Nullable T t);
}
