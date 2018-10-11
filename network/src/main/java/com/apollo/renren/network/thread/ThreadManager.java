package com.apollo.renren.network.thread;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 线程管理
 */
public class ThreadManager {
    /**
     * 运行在工作线程
     *
     * @param subscribeListener
     * @param observerListener
     * @param <T>
     */
    public static <T> void execute(final SubscribeListener<T> subscribeListener,
                                   ObserverListener<T> observerListener) {
        Observable.create((ObservableOnSubscribe<T>) emitter -> {
            emitter.onNext(subscribeListener.runOnSubThread());
            emitter.onComplete();
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerListener::runOnUIThread);
    }

    /**
     * 运行在主线程
     *
     * @param runnable
     */
    public static void runOnUIThread(Runnable runnable) {
        Observable.just(runnable)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Runnable::run);
    }
}
