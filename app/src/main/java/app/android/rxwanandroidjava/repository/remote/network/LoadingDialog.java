package app.android.rxwanandroidjava.repository.remote.network;

import androidx.core.widget.ContentLoadingProgressBar;

import app.android.rxwanandroidjava.utils.ActivityManagerUtil;

/**
 * 描述：网络请求加载框
 */
class LoadingDialog {

    private ContentLoadingProgressBar mLoadingProgressBar;

    public static LoadingDialog getInstance() {
        return LoadingDialogInstance.INSTANCE;
    }

    public void show() {
        if (mLoadingProgressBar == null) {
            mLoadingProgressBar = new ContentLoadingProgressBar(ActivityManagerUtil.getInstance().currentActivity());
        }
        mLoadingProgressBar.show();
    }

    public void hide() {
        if (mLoadingProgressBar != null) {
            mLoadingProgressBar.hide();
        }

    }

    private static class LoadingDialogInstance {
        private static final LoadingDialog INSTANCE = new LoadingDialog();
    }
}
