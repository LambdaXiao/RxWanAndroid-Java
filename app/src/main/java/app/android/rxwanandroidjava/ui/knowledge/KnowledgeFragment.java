package app.android.rxwanandroidjava.ui.knowledge;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import app.android.rxwanandroidjava.R;
import app.android.rxwanandroidjava.common.base.BaseFragment;

public class KnowledgeFragment extends BaseFragment {

    private KnowledgeViewModel mViewModel;

    public static KnowledgeFragment newInstance() {
        return new KnowledgeFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.knowledge_fragment;
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
        mViewModel = ViewModelProviders.of(this).get(KnowledgeViewModel.class);
        // TODO: Use the ViewModel
    }

}
