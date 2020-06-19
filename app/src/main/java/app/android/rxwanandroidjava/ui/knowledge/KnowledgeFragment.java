package app.android.rxwanandroidjava.ui.knowledge;

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
import app.android.rxwanandroidjava.databinding.KnowledgeFragmentBinding;

public class KnowledgeFragment extends BaseFragment {

    private KnowledgeFragmentBinding mFragmentBinding;
    private KnowledgeViewModel mViewModel;
    private TextView mTitle;

    public static KnowledgeFragment newInstance() {
        return new KnowledgeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.knowledge_fragment, container, false);
        mTitle = mFragmentBinding.getRoot().findViewById(R.id.common_toolbar_title_tv);
        mTitle.setText(getString(R.string.menu_knowledge));
        return mFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(KnowledgeViewModel.class);
        // TODO: Use the ViewModel
    }

}
