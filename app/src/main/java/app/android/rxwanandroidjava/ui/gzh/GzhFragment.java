package app.android.rxwanandroidjava.ui.gzh;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import app.android.rxwanandroidjava.R;
import app.android.rxwanandroidjava.common.base.BaseFragment;

public class GzhFragment extends BaseFragment {

    private GzhViewModel mViewModel;
    private TextView mTitle;

    public static GzhFragment newInstance() {
        return new GzhFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.gzh_fragment;
    }

    @Override
    protected void initLayout(View view) {
        mTitle = view.findViewById(R.id.common_toolbar_title_tv);
        mTitle.setText(getString(R.string.menu_gzh));
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
