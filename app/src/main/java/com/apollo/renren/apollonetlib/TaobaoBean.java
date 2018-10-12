package com.apollo.renren.apollonetlib;

public class TaobaoBean {


    /**
     * status : 201
     * message : APP被用户自己禁用，请在控制台解禁
     */

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TaobaoBean{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
