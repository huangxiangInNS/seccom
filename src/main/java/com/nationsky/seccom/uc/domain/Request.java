package com.nationsky.seccom.uc.domain;


public class Request <T>
{
	private OperId operId;
    private T requestData;

    public OperId getOperId() {
        return operId;
    }

    public void setOperId(OperId operId) {
        this.operId = operId;
    }

    public T getRequestData() {
        return requestData;
    }

    public void setRequestData(T requestData) {
        this.requestData = requestData;
    }
}
