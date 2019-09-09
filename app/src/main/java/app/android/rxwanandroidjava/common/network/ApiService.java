package app.android.rxwanandroidjava.common.network;

import java.util.List;

import app.android.rxwanandroidjava.ui.home.bean.BannerBean;
import app.android.rxwanandroidjava.ui.home.bean.FeedArticleList;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 描述：API配置
 */
public interface ApiService {
    public static String HOST = "https://www.wanandroid.com/";

    @GET("article/list/{pagenum}/json")
    Observable<BaseResponse<FeedArticleList>> getHomeArticle(@Path("pagenum") int pagenum);

    @GET("banner/json")
    Observable<BaseResponse<List<BannerBean>>> getBanner();
}
