package com.cooper.rpc.body;

/**
 * Created by emirbobo on 2016/9/30.
 */
public class ResponseBody implements java.io.Serializable {

    private int uid;

    private int resultCode;

    private Object invokeResult;

    public ResponseBody() {
    }

    public ResponseBody(int resultCode, Object jsonResult) {
        this.resultCode = resultCode;
        this.invokeResult = jsonResult;
    }

    public ResponseBody(int uid, int resultCode, Object jsonResult) {
        this.uid = uid;
        this.resultCode = resultCode;
        this.invokeResult = jsonResult;
    }

    public int getResultCode() {

        return resultCode;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Object getInvokeResult() {
        return invokeResult;
    }

    public void setInvokeResult(Object invokeResult) {
        this.invokeResult = invokeResult;
    }

    @Override
    public String toString() {
        return "ResponseBody{" +
                "uid=" + uid +
                ", resultCode=" + resultCode +
                ", invokeResult='" + invokeResult + '\'' +
                '}';
    }
}
