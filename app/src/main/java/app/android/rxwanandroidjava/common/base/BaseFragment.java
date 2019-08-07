package app.android.rxwanandroidjava.common.base;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import app.android.rxwanandroidjava.application.MyApplication;


/**
 * 基类Fragment
 */
public abstract class BaseFragment extends Fragment {
    /**
     * DEBUG标签
     */
    private static final String DEBUG_TAG = "BaseFragment";
    /**
     * 关联的Activity
     */
    protected Activity mActivity;
    /**
     * 全局Application实例
     */
    protected MyApplication mApplication;

    /**
     * 虚函数，继承此类指定Activity的layout布局文件
     */
    protected abstract int getLayoutResource();

    /**
     * 虚函数，继承此类初始化个性化的布局
     */
    protected abstract void initLayout(View view);

    /**
     * 虚函数，继承此类初始化相关数据
     */
    protected abstract void initData();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mActivity = getActivity();
        } catch (Exception e) {
            Log.w(DEBUG_TAG, "activity no extend BaseActivity");
        }
        mApplication = (MyApplication) mActivity.getApplication();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResource(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initLayout(view);
        initData();
    }


}
