package app.android.rxwanandroidjava.common.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * 描述：需要用到 DataBinding 的地方可继承此基类
 */
public abstract class BaseDataBindingFragment<DB extends ViewDataBinding> extends BaseFragment {

    protected DB mDataBind;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mDataBind = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        // 让xml内绑定的LiveData和Observer建立连接，让LiveData能感知Fragment的生命周期
        mDataBind.setLifecycleOwner(getViewLifecycleOwner());
        return mDataBind.getRoot();
    }
}
