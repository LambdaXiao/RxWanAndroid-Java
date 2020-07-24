package app.android.rxwanandroidjava.repository.remote;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import app.android.rxwanandroidjava.ui.home.bean.BannerBean;
import app.android.rxwanandroidjava.ui.home.bean.FeedArticleList;

/**
 * 远程请求标准接口（在仓库里面） 也就是网络Network请求
 * 只为 RemoteRepository 服务
 */
public interface IRemoteRequest {

    // 得到首页banner
    void getBanner(LifecycleOwner lifecycleOwner, MutableLiveData<List<BannerBean>> bannerData);

    // 得到首页文章列表
    void getArticleList(LifecycleOwner lifecycleOwner, MutableLiveData<FeedArticleList> feedArticleList, int pagenum);
}
