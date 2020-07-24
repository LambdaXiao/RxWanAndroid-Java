package app.android.rxwanandroidjava.repository.remote;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.List;

import app.android.rxwanandroidjava.repository.remote.network.ApiClient;
import app.android.rxwanandroidjava.repository.remote.network.ObserverWrapper;
import app.android.rxwanandroidjava.repository.remote.network.ResponseWrapper;
import app.android.rxwanandroidjava.ui.home.bean.BannerBean;
import app.android.rxwanandroidjava.ui.home.bean.FeedArticleList;

/**
 * 描述：仓库角色 网络请求  单例
 */
public class RemoteRepository implements IRemoteRequest {

    public static RemoteRepository getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 静态内部类方式创建单例模式
     */
    private static class Holder {
        private static final RemoteRepository INSTANCE = new RemoteRepository();
    }

    @Override
    public void getBanner(LifecycleOwner lifecycleOwner, MutableLiveData<List<BannerBean>> bannerData) {
        ApiClient.getInstance().getService().getBanner()
                .compose(ApiClient.getInstance().threadTransformer())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner)))
                .subscribe(new ObserverWrapper<List<BannerBean>>(true) {
                    @Override
                    public void onSuccess(List<BannerBean> data) {
                        bannerData.setValue(data);
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg, ResponseWrapper<List<BannerBean>> response) {

                    }
                });
    }

    @Override
    public void getArticleList(LifecycleOwner lifecycleOwner, MutableLiveData<FeedArticleList> feedArticleList, int pagenum) {
        ApiClient.getInstance().getService().getHomeArticle(pagenum)
                .compose(ApiClient.getInstance().threadTransformer())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner)))
                .subscribe(new ObserverWrapper<FeedArticleList>(true) {
                    @Override
                    public void onSuccess(FeedArticleList data) {
                        feedArticleList.setValue(data);
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg, ResponseWrapper<FeedArticleList> response) {

                    }
                });
    }
}
