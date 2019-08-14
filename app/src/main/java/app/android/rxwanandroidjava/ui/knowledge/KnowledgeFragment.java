package app.android.rxwanandroidjava.ui.knowledge;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import app.android.rxwanandroidjava.R;
import app.android.rxwanandroidjava.common.base.BaseFragment;

public class KnowledgeFragment extends BaseFragment {

    private KnowledgeViewModel mViewModel;
    private TextView mTitle;

    public static KnowledgeFragment newInstance() {
        return new KnowledgeFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.knowledge_fragment;
    }

    @Override
    protected void initLayout(View view) {
        mTitle = view.findViewById(R.id.common_toolbar_title_tv);
        mTitle.setText(getString(R.string.menu_knowledge));
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
