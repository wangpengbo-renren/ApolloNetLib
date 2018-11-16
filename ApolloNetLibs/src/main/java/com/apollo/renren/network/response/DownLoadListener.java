package com.apollo.renren.network.response;

public interface DownLoadListener {

    void onSuccess();

    void onFailure();

    void onUpdate(float progress);

}
