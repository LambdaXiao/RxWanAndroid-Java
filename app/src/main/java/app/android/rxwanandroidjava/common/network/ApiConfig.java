package app.android.rxwanandroidjava.common.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 描述：API配置
 */
public interface ApiConfig {
    public static String HOST = "https://www.wanandroid.com/";

    @GET("article/list/{pagenum}/json")
    Call<BaseData> getHomeArticle(@Path("pagenum") int pagenum);
}
