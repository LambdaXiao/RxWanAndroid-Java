package app.android.rxwanandroidjava.common.network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 描述：Retrofit网络请求统一管理
 */
public class RetrofitManager {

    private Retrofit mRetrofit;
    private ApiConfig mApiConfig;

    private RetrofitManager() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiConfig.HOST)
                    .addConverterFactory(GsonConverterFactory.create())   //数据解析器
                    .build();
            mApiConfig = mRetrofit.create(ApiConfig.class);
        }
    }

    public static RetrofitManager getInstance() {
        return RetrofitManagerInstance.mRetrofitManager;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public ApiConfig getApiConfig() {
        return mApiConfig;
    }

    /**
     * 通用网络请求方法
     *
     * @param call
     * @param mCallBack
     */
    public void enqueue(Call<BaseData> call, CallBack mCallBack) {
        call.enqueue(new Callback<BaseData>() {
            @Override
            public void onResponse(Call<BaseData> call, Response<BaseData> response) {
                try {
                    BaseData baseData = response.body();
                    Double errorCode = baseData.getErrorCode();
                    String errorMsg = baseData.getErrorMsg();
                    if (errorCode == 0) {
                        //请求成功
                        mCallBack.onSuccess(baseData);
                    } else {
                        //请求失败
                        mCallBack.onFailure(errorCode, errorMsg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //数据解析失败
                    mCallBack.onFailure(-1, "数据解析异常，请重试！");
                }
            }

            @Override
            public void onFailure(Call<BaseData> call, Throwable t) {
                //网络请求失败
                mCallBack.onFailure(-1, "请求异常，请重试！");
            }
        });
    }

    /**
     * 数据回调
     */
    public interface CallBack {
        void onSuccess(Object object);

        void onFailure(double errorCode, String errorMsg);
    }

    /**
     * 静态内部类方式创建单例模式
     */
    private static class RetrofitManagerInstance {
        private static final RetrofitManager mRetrofitManager = new RetrofitManager();
    }

}
