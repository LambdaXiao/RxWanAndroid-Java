package app.android.rxwanandroidjava.ui.project;

import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import app.android.rxwanandroidjava.R;
import app.android.rxwanandroidjava.common.base.BaseDataBindingFragment;
import app.android.rxwanandroidjava.databinding.ProjectFragmentBinding;

public class ProjectFragment extends BaseDataBindingFragment<ProjectFragmentBinding> {

    private ProjectViewModel mViewModel;
    private TextView mTitle;

    public static ProjectFragment newInstance() {
        return new ProjectFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.project_fragment;
    }

    @Override
    protected void initView() {
        mViewModel = new ViewModelProvider(this).get(ProjectViewModel.class);

        mTitle = mDataBind.getRoot().findViewById(R.id.common_toolbar_title_tv);
        mTitle.setText(getString(R.string.menu_project));
    }

    @Override
    protected void initData() {

    }
}
