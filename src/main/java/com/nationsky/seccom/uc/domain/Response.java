package com.nationsky.seccom.uc.domain;

public class Response<V>
{
	private Result result;
    private V responseData;
    
    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public V getResponseData() {
        return responseData;
    }

    public void setResponseData(V resultData) {
        this.responseData = resultData;
    }
}
