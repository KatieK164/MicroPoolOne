package com.micropool;

public class ShotResponse {

    private String result;
    private int resultCode;

    public ShotResponse() {
    }

    public ShotResponse(String result, int resultCode) {
        this.result = result;
        this.resultCode = resultCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}
