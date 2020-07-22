package app.android.rxwanandroidjava.common.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import app.android.rxwanandroidjava.application.MyApplication;
import app.android.rxwanandroidjava.common.lifecycleobserver.ActivityLifecycleObserver;
import app.android.rxwanandroidjava.utils.ActivityManagerUtil;


/**
 * 所有Activity的父类
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 全局的MyApplication实例
     */
    protected MyApplication mApplication;
    protected BaseActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApplication = (MyApplication) getApplication();
        mActivity = this;
        //Activity堆栈管理
        ActivityManagerUtil.getInstance().pushActivity(this);

        getLifecycle().addObserver(ActivityLifecycleObserver.getInstance());

        setContentLayout();
    }

    protected void setContentLayout() {
        setContentView(getLayoutId());
        initView();
        initData();
    }

    /**
     * 子类必须实现的方法
     */
    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //结束Activity&从栈中移除该Activity
        ActivityManagerUtil.getInstance().popActivity(this);
    }

}
