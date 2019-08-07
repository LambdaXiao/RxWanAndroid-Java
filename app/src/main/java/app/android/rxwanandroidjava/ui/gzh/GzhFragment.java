package app.android.rxwanandroidjava.ui.gzh;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import app.android.rxwanandroidjava.R;
import app.android.rxwanandroidjava.common.base.BaseFragment;

public class GzhFragment extends BaseFragment {

    private GzhViewModel mViewModel;

    public static GzhFragment newInstance() {
        return new GzhFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.gzh_fragment;
    }

    @Override
    protected void initLayout(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(GzhViewModel.class);
        // TODO: Use the ViewModel
    }

}
