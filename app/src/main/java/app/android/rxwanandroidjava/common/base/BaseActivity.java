package app.android.rxwanandroidjava.common.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import app.android.rxwanandroidjava.application.MyApplication;
import app.android.rxwanandroidjava.common.GlobalViewModel;
import app.android.rxwanandroidjava.common.lifecycleobserver.ActivityLifecycleObserver;
import app.android.rxwanandroidjava.utils.ActivityManagerUtil;


/**
 * 所有Activity的父类
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * 全局的MyApplication实例
     */
    protected MyApplication mApplication;
    protected BaseActivity mActivity;
    // 贯穿整个项目的,初始化全局共享（单例的方式 MyApplication传入的ViewModelStore是同一个）
    protected GlobalViewModel mSharedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (MyApplication) getApplication();
        mActivity = this;
        //Activity堆栈管理
        ActivityManagerUtil.getInstance().pushActivity(this);

        mSharedViewModel = mApplication.getAppViewModelProvider(this).get(GlobalViewModel.class);

        getLifecycle().addObserver(ActivityLifecycleObserver.getInstance());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //结束Activity&从栈中移除该Activity
        ActivityManagerUtil.getInstance().popActivity(this);
    }
}
