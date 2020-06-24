package app.android.rxwanandroidjava.repository.remote;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import app.android.rxwanandroidjava.repository.remote.network.BaseObserver;
import app.android.rxwanandroidjava.repository.remote.network.BaseResponse;
import app.android.rxwanandroidjava.repository.remote.network.RetrofitManager;
import app.android.rxwanandroidjava.ui.home.bean.BannerBean;
import app.android.rxwanandroidjava.ui.home.bean.FeedArticleList;

/**
 * 描述：仓库角色 网络请求  单例
 */
public class RemoteRepository implements IRemoteRequest {

    private static RemoteRepository sRemoteRepository;

    public static RemoteRepository getInstance() {
        if (sRemoteRepository == null) {
            synchronized (RemoteRepository.class) {
                if (sRemoteRepository == null) {
                    sRemoteRepository = new RemoteRepository();
                }
            }
        }
        return sRemoteRepository;
    }

    @Override
    public void getBanner(MutableLiveData<List<BannerBean>> bannerData) {
        RetrofitManager.getInstance().getService().getBanner()
                .compose(RetrofitManager.getInstance().threadTransformer())
                .subscribe(new BaseObserver<List<BannerBean>>(true) {
                    @Override
                    public void onSuccess(List<BannerBean> data) {
                        bannerData.setValue(data);
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg, BaseResponse<List<BannerBean>> response) {

                    }
                });
    }

    @Override
    public void getArticleList(MutableLiveData<FeedArticleList> feedArticleList, int pagenum) {
        RetrofitManager.getInstance().getService().getHomeArticle(pagenum)
                .compose(RetrofitManager.getInstance().threadTransformer())
                .subscribe(new BaseObserver<FeedArticleList>(true) {
                    @Override
                    public void onSuccess(FeedArticleList data) {
                        feedArticleList.setValue(data);
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg, BaseResponse<FeedArticleList> response) {

                    }
                });
    }
}
