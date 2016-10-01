package com.cooper.rpc.body;

import java.util.Arrays;

/**
 * Created by emirbobo on 2016/9/30.
 */
public class RequestBody implements java.io.Serializable {

    String messageId;
    String className;
    String methodName;
    Object[] params;

    public RequestBody() {
    }

    public RequestBody(String messageId, String className, String methodName, Object[] params) {

        this.messageId = messageId;
        this.className = className;
        this.methodName = methodName;
        this.params = params;
    }

    public String getMessageId() {

        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "RequestBody{" +
                "messageId='" + messageId + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params=" + Arrays.toString(params) +
                '}';
    }
}
