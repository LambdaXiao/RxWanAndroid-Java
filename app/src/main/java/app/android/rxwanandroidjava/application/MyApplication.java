package app.android.rxwanandroidjava.application;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述：
 */
public class MyApplication extends BaseApp{

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    public static MyApplication getApplication() {
        return (MyApplication) getApp();
    }

}
