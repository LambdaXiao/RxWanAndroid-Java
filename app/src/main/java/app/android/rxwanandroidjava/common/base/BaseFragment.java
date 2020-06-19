package app.android.rxwanandroidjava.common.base;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import app.android.rxwanandroidjava.application.MyApplication;
import app.android.rxwanandroidjava.common.GlobalViewModel;
import app.android.rxwanandroidjava.common.lifecycleobserver.FragmentLifecycleObserver;


/**
 * 所有Fragment的父类
 */
public abstract class BaseFragment extends Fragment {

    public GlobalViewModel sharedViewModel;  // 全局共享
    protected AppCompatActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = getAppViewModelProvider().get(GlobalViewModel.class);

        getLifecycle().addObserver(FragmentLifecycleObserver.getInstance());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }


    // 给当前BaseFragment用的
    private ViewModelProvider getAppViewModelProvider() {
        return ((MyApplication) mActivity.getApplicationContext()).getAppViewModelProvider(mActivity);
    }


}
