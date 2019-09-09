package app.android.rxwanandroidjava.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import app.android.rxwanandroidjava.common.network.BaseObserver;
import app.android.rxwanandroidjava.common.network.BaseResponse;
import app.android.rxwanandroidjava.common.network.RetrofitManager;
import app.android.rxwanandroidjava.ui.home.bean.BannerBean;
import app.android.rxwanandroidjava.ui.home.bean.FeedArticleList;

public class HomeViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<FeedArticleList> mLiveData;
    MutableLiveData<List<BannerBean>> mBannerData;

    public HomeViewModel() {
        mBannerData = new MutableLiveData<List<BannerBean>>();
        mLiveData = new MutableLiveData<FeedArticleList>();
    }

    public MutableLiveData<List<BannerBean>> getBannerData() {
        return mBannerData;
    }

    public MutableLiveData<FeedArticleList> getLiveData() {
        return mLiveData;
    }


    public void getBanner() {
        RetrofitManager.getInstance().getService().getBanner()
                .compose(RetrofitManager.getInstance().threadTransformer())
                .subscribe(new BaseObserver<BaseResponse<List<BannerBean>>>() {
                    @Override
                    public void onSuccess(BaseResponse<List<BannerBean>> response) {
                        List<BannerBean> bannerList = response.getData();
                        mBannerData.setValue(bannerList);
                    }
                });
    }

    /**
     * 获取首页文章列表
     */
    public void getArticle(int pagenum) {
        RetrofitManager.getInstance().getService().getHomeArticle(pagenum)
                .compose(RetrofitManager.getInstance().threadTransformer())
                .subscribe(new BaseObserver<BaseResponse<FeedArticleList>>() {
                    @Override
                    public void onSuccess(BaseResponse<FeedArticleList> response) {
                        FeedArticleList mArticleData = response.getData();
                        mLiveData.setValue(mArticleData);
                    }
                });
    }
}
