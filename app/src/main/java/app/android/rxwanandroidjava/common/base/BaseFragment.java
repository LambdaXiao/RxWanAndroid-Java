package app.android.rxwanandroidjava.common.base;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import app.android.rxwanandroidjava.common.lifecycleobserver.FragmentLifecycleObserver;


/**
 * 所有Fragment的父类
 */
public abstract class BaseFragment extends Fragment {

    protected AppCompatActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLifecycle().addObserver(FragmentLifecycleObserver.getInstance());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // 缓存当前依附的activity
        mActivity = (AppCompatActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    /**
     * 子类必须实现的方法
     */
    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();
}
