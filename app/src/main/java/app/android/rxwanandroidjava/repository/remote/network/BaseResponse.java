package app.android.rxwanandroidjava.repository.remote.network;

import java.io.Serializable;

import app.android.rxwanandroidjava.common.Constants;

/**
 * 描述：网络请求返回的实体类Bean
 */
public class BaseResponse<T> implements Serializable {

    private T data;
    private int errorCode;
    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return getErrorCode() == Constants.SUCCESS_CODE;
    }
}
