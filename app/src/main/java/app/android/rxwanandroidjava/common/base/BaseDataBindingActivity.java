package app.android.rxwanandroidjava.common.base;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * 需要用到 DataBinding 的地方可继承此基类
 */
public abstract class BaseDataBindingActivity<DB extends ViewDataBinding> extends BaseActivity {

    protected DB mDataBind;

    @Override
    protected void setContentLayout() {
        mDataBind = DataBindingUtil.setContentView(this, getLayoutId());
        // 让xml内绑定的LiveData和Observer建立连接，让LiveData能感知Activity的生命周期
        mDataBind.setLifecycleOwner(mActivity);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDataBind != null) {
            mDataBind.unbind();
        }
    }
}