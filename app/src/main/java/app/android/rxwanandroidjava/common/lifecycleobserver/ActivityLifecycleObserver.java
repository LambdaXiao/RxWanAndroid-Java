package app.android.rxwanandroidjava.common.lifecycleobserver;

import android.content.IntentFilter;
import android.net.ConnectivityManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import app.android.rxwanandroidjava.common.networkstate.NetworkStateReceive;

/**
 * 描述：所有Activity的生命周期监听
 */
public class ActivityLifecycleObserver implements DefaultLifecycleObserver {

    private NetworkStateReceive mNetworkStateReceive;
    private IntentFilter filter;

    public static ActivityLifecycleObserver getInstance() {
        return LifecycleObserverInstance.INSTANCE;
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        if (owner instanceof AppCompatActivity) {
            mNetworkStateReceive = new NetworkStateReceive();
            filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        }
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {

    }

    /**
     * 观察 BaseActivity 的 生命周期方法后
     * 就是注册一个 广播，此广播可以接收到信息（然后 输出 “网络不给力”）
     */
    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        if (owner instanceof AppCompatActivity) {
            ((AppCompatActivity) owner).registerReceiver(mNetworkStateReceive, filter);
        }
    }

    /**
     * 那么观察到 观察 BaseActivity的生命周期方法后
     * 就是移除一个 广播
     *
     * @param owner
     */
    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        if (owner instanceof AppCompatActivity) {
            ((AppCompatActivity) owner).unregisterReceiver(mNetworkStateReceive);
        }
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {

    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {

    }

    /*
     * 静态内部类方式实现单例模式
     *
     *当第一次加载Singleton类时并不会初始化SINGLRTON,只有第一次调用getInstance方法的时候才会初始化SINGLETON
     *第一次调用getInstance 方法的时候虚拟机才会加载SingletonHoder类,这种方式不仅能够保证线程安全,也能够保证对象的唯一,
     *还延迟了单例的实例化,所有推荐使用这种方式
     * */
    private static class LifecycleObserverInstance {
        private static final ActivityLifecycleObserver INSTANCE = new ActivityLifecycleObserver();
    }
}
