package app.android.rxwanandroidjava.application;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.multidex.MultiDex;

import app.android.rxwanandroidjava.common.GlobalViewModel;

/**
 * 描述：整个项目的 Application
 * <p>
 * 为什么要 “implements ViewModelStoreOwner” 就是为了 让整个项目持有一份 被观察者接口，为了完成全局共享
 */
public class MyApplication extends BaseApp implements ViewModelStoreOwner {

    private ViewModelStore mAppViewModelStore;
    // 贯穿整个项目的,初始化全局共享（单例的方式 MyApplication传入的ViewModelStore是同一个）
    public GlobalViewModel mSharedViewModel;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAppViewModelStore = new ViewModelStore();
        mSharedViewModel = new ViewModelProvider(getApplication(), ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(GlobalViewModel.class);
        // todo，这里可以完成一系列的初始化工作

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

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mAppViewModelStore;
    }


}
