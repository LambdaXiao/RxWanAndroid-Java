package app.android.rxwanandroidjava.repository.remote.network;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import app.android.rxwanandroidjava.common.Constants;
import app.android.rxwanandroidjava.utils.ActivityManagerUtil;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 描述：观察者（数据回调处理基类）
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    private boolean isShow = false;
    private LoadingDialog mLoadingDialog;

    public BaseObserver(boolean isShow) {
        this.isShow = isShow;
    }

    @Override
    public void onSubscribe(Disposable d) {
        // todo 显示加载框
        if (isShow) {
            if (mLoadingDialog == null) {
                mLoadingDialog = new LoadingDialog(ActivityManagerUtil.getInstance().currentActivity());
            }
            mLoadingDialog.showDialog();
        }
    }

    @Override
    public void onNext(BaseResponse<T> response) {
        try {
            if (response.isSuccess()) {
                //请求成功
                onSuccess(response.getData());
            } else {
                //请求失败
                onFailure(response.getErrorCode(), response.getErrorMsg(), response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //数据解析失败
            onFailure(Constants.ERROR_CODE, e.getMessage(), response);
        }
    }

    @Override
    public void onError(Throwable e) {
        // todo 隐藏加载框
        if (mLoadingDialog != null) {
            mLoadingDialog.hideDialog();
        }

        if (e instanceof retrofit2.HttpException) {
            //HTTP错误
            onFailure(Constants.ERROR_CODE, "网络(协议)异常！", null);
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            //连接错误
            onFailure(Constants.ERROR_CODE, "连接失败！", null);
        } else if (e instanceof InterruptedIOException) {
            //连接超时
            onFailure(Constants.ERROR_CODE, "连接超时！", null);
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            //解析错误
            onFailure(Constants.ERROR_CODE, "数据解析异常！", null);
        } else {
            //其他错误
            onFailure(Constants.ERROR_CODE, "其他未知错误！", null);
        }
    }

    @Override
    public void onComplete() {
        // todo 隐藏加载框
        if (mLoadingDialog != null) {
            mLoadingDialog.hideDialog();
        }
    }

    public abstract void onSuccess(T data);

    public abstract void onFailure(int errorCode, String errorMsg, BaseResponse<T> response);
}
