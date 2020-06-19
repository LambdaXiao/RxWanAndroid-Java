package app.android.rxwanandroidjava.ui.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import app.android.rxwanandroidjava.R;
import app.android.rxwanandroidjava.common.base.BaseFragment;
import app.android.rxwanandroidjava.databinding.ProjectFragmentBinding;

public class ProjectFragment extends BaseFragment {

    private ProjectFragmentBinding mFragmentBinding;
    private ProjectViewModel mViewModel;
    private TextView mTitle;

    public static ProjectFragment newInstance() {
        return new ProjectFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.project_fragment, container, false);
        mTitle = mFragmentBinding.getRoot().findViewById(R.id.common_toolbar_title_tv);
        mTitle.setText(getString(R.string.menu_project));
        return mFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProjectViewModel.class);
        // TODO: Use the ViewModel
    }

}
