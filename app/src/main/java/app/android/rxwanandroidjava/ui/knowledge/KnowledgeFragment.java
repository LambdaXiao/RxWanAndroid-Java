package app.android.rxwanandroidjava.ui.knowledge;

import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import app.android.rxwanandroidjava.R;
import app.android.rxwanandroidjava.common.base.BaseDataBindingFragment;
import app.android.rxwanandroidjava.databinding.KnowledgeFragmentBinding;

public class KnowledgeFragment extends BaseDataBindingFragment<KnowledgeFragmentBinding> {

    private KnowledgeViewModel mViewModel;
    private TextView mTitle;

    public static KnowledgeFragment newInstance() {
        return new KnowledgeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.knowledge_fragment;
    }

    @Override
    protected void initView() {
        mViewModel = new ViewModelProvider(this).get(KnowledgeViewModel.class);

        mTitle = mDataBind.getRoot().findViewById(R.id.common_toolbar_title_tv);
        mTitle.setText(getString(R.string.menu_knowledge));
    }

    @Override
    protected void initData() {

    }
}
