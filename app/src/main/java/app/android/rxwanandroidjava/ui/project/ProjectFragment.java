package app.android.rxwanandroidjava.ui.project;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import app.android.rxwanandroidjava.R;
import app.android.rxwanandroidjava.common.base.BaseFragment;

public class ProjectFragment extends BaseFragment {

    private ProjectViewModel mViewModel;

    public static ProjectFragment newInstance() {
        return new ProjectFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.project_fragment;
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
        mViewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);
        // TODO: Use the ViewModel
    }

}
