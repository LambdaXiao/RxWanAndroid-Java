package app.android.rxwanandroidjava.common.network;

import app.android.rxwanandroidjava.ui.home.bean.FeedArticleListData;

/**
 * 描述：
 */
public class BaseData {
    FeedArticleListData data;
    double errorCode;
    String errorMsg;

    public FeedArticleListData getData() {
        return data;
    }

    public void setData(FeedArticleListData data) {
        this.data = data;
    }

    public double getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(double errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
