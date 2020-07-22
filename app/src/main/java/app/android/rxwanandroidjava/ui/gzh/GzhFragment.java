package app.android.rxwanandroidjava.ui.gzh;

import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import app.android.rxwanandroidjava.R;
import app.android.rxwanandroidjava.common.base.BaseDataBindingFragment;
import app.android.rxwanandroidjava.databinding.GzhFragmentBinding;

public class GzhFragment extends BaseDataBindingFragment<GzhFragmentBinding> {

    private GzhViewModel mViewModel;
    private TextView mTitle;

    public static GzhFragment newInstance() {
        return new GzhFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.gzh_fragment;
    }

    @Override
    protected void initView() {
        mViewModel = new ViewModelProvider(this).get(GzhViewModel.class);

        mTitle = mDataBind.getRoot().findViewById(R.id.common_toolbar_title_tv);
        mTitle.setText(getString(R.string.menu_gzh));
    }

    @Override
    protected void initData() {

    }
}
