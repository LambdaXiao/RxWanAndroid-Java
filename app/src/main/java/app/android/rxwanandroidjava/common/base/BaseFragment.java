package app.android.rxwanandroidjava.common.base;


import android.content.Context;
import android.os.Bundle;

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
        mActivity = (AppCompatActivity) context;
    }



}
