package com.bean;

public class RequestBean<T> {
    private String reqId = null;
    private T reqParam = null;
    private ReqPageInfo reqPageInfo = null;

    public RequestBean(){

    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public T getReqParam() {
        return reqParam;
    }

    public void setReqParam(T reqParam) {
        this.reqParam = reqParam;
    }

    public ReqPageInfo getReqPageInfo() {
        return reqPageInfo;
    }

    public void setReqPageInfo(ReqPageInfo reqPageInfo) {
        this.reqPageInfo = reqPageInfo;
    }
}
