package app.android.rxwanandroidjava.ui.gzh;

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
import app.android.rxwanandroidjava.databinding.GzhFragmentBinding;

public class GzhFragment extends BaseFragment {

    private GzhFragmentBinding mFragmentBinding;
    private GzhViewModel mViewModel;
    private TextView mTitle;

    public static GzhFragment newInstance() {
        return new GzhFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.gzh_fragment, container, false);

        mTitle = mFragmentBinding.getRoot().findViewById(R.id.common_toolbar_title_tv);
        mTitle.setText(getString(R.string.menu_gzh));

        return mFragmentBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GzhViewModel.class);
        // TODO: Use the ViewModel
    }

}
