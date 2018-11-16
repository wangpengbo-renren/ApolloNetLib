package com.apollo.renren.network.response;

public interface UploadListener {
    void onSuccess();

    void onFailure();

    void onProgress(float progress);
}
