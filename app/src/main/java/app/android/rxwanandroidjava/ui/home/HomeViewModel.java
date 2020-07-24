package app.android.rxwanandroidjava.ui.home;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import app.android.rxwanandroidjava.repository.remote.RemoteRepository;
import app.android.rxwanandroidjava.ui.home.bean.BannerBean;
import app.android.rxwanandroidjava.ui.home.bean.FeedArticleList;

public class HomeViewModel extends ViewModel {

    MutableLiveData<FeedArticleList> mArticleList;
    MutableLiveData<List<BannerBean>> mBannerData;

    public HomeViewModel() {
        mBannerData = new MutableLiveData<List<BannerBean>>();
        mArticleList = new MutableLiveData<FeedArticleList>();
    }

    public MutableLiveData<List<BannerBean>> getBannerData() {
        return mBannerData;
    }

    public MutableLiveData<FeedArticleList> getArticleList() {
        return mArticleList;
    }

    /**
     * 首页banner
     */
    public void requestBanner(LifecycleOwner lifecycleOwner) {
        RemoteRepository.getInstance().getBanner(lifecycleOwner, getBannerData());
    }

    /**
     * 获取首页文章列表
     */
    public void requestArticleList(LifecycleOwner lifecycleOwner, int pagenum) {
        RemoteRepository.getInstance().getArticleList(lifecycleOwner, getArticleList(), pagenum);
    }
}
