package app.android.rxwanandroidjava.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import app.android.rxwanandroidjava.common.network.BaseObserver;
import app.android.rxwanandroidjava.common.network.BaseResponse;
import app.android.rxwanandroidjava.common.network.RetrofitManager;
import app.android.rxwanandroidjava.ui.home.bean.FeedArticleListData;

public class HomeViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<FeedArticleListData> mLiveData;

    public HomeViewModel() {
        mLiveData = new MutableLiveData<FeedArticleListData>();
    }

    public MutableLiveData<FeedArticleListData> getLiveData() {
        return mLiveData;
    }

    /**
     * 获取首页文章列表
     */
    public void getArticle() {
        RetrofitManager.getInstance().getService().getHomeArticle(0)
                .compose(RetrofitManager.getInstance().threadTransformer())
                .subscribe(new BaseObserver<BaseResponse<FeedArticleListData>>() {
                    @Override
                    public void onSuccess(BaseResponse<FeedArticleListData> response) {
                        FeedArticleListData mArticleData = response.getData();
                        mLiveData.setValue(mArticleData);
                    }
                });
    }
}
