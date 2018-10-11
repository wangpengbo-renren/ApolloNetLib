package com.apollo.renren.network.http;

import com.apollo.renren.network.constants.HttpCode;

/**
 * http相关参数构建类
 */
public class HttpBuilder {
    private int readTimeOut = 30_000;
    private int writeTimeOut = 30_000;
    private int connectTimeOut = 30_000;

    public HttpBuilder setTimeOut(int timeOut) {
        setReadTimeOut(timeOut);
        setWriteTimeOut(timeOut);
        setConnectTimeOut(timeOut);
        return this;
    }

    public int getReadTimeOut() {
        return readTimeOut;
    }

    public HttpBuilder setReadTimeOut(int readTimeOut) {
        this.readTimeOut = readTimeOut;
        return this;
    }

    public int getWriteTimeOut() {
        return writeTimeOut;
    }

    public HttpBuilder setWriteTimeOut(int writeTimeOut) {
        this.writeTimeOut = writeTimeOut;
        return this;
    }

    public int getConnectTimeOut() {
        return connectTimeOut;
    }

    public HttpBuilder setConnectTimeOut(int connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
        return this;
    }

    public int getCodeSuccess() {
        return HttpCode.CODE_SUCCESS;
    }

    public HttpBuilder setCodeSuccess(int codeSuccess) {
        HttpCode.CODE_SUCCESS = codeSuccess;
        return this;
    }

    public int getCodeFailure() {
        return HttpCode.CODE_FAILURE;
    }

    public HttpBuilder setCodeFailure(int codeFailure) {
        HttpCode.CODE_FAILURE = codeFailure;
        return this;
    }
}
