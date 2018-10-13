package com.apollo.renren.network.http.retrofit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import com.apollo.renren.network.response.Callback;

public class RetrofigManager implements IRetroifitManager {
    @Override
    public <T> void doGet(@NonNull String tag, @NonNull String url, @Nullable ArrayMap<String, Object> params, @Nullable Callback<T> callback) {

    }

    @Override
    public <T> void doPost(@NonNull String tag, @NonNull String url, @Nullable ArrayMap<String, Object> params, @Nullable Callback<T> callback) {

    }

    @Override
    public void cancelRequestByTag(Object tag) {

    }

    @Override
    public void cacelAllRequests() {

    }
}
