package app.android.rxwanandroidjava.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import app.android.rxwanandroidjava.R;
import app.android.rxwanandroidjava.common.base.BaseFragment;
import app.android.rxwanandroidjava.ui.home.adapter.ArticleListAdapter;
import app.android.rxwanandroidjava.ui.home.bean.FeedArticleData;


public class HomeFragment extends BaseFragment {

    private HomeViewModel mViewModel;
    private TextView mTitle;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;
    private ArticleListAdapter articleListAdapter;
    private List<FeedArticleData> mFeedArticleDataList;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initLayout(View view) {
        mTitle = view.findViewById(R.id.common_toolbar_title_tv);
        mTitle.setText(getString(R.string.menu_home));

        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        mRecyclerView = view.findViewById(R.id.mRecyclerView);
        mSmartRefreshLayout = view.findViewById(R.id.mSmartRefreshLayout);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);

    }

    @Override
    protected void initData() {
        mFeedArticleDataList = new ArrayList<FeedArticleData>();
        articleListAdapter = new ArticleListAdapter(getActivity(), mFeedArticleDataList);
        mRecyclerView.setAdapter(articleListAdapter);

        mViewModel.getLiveData().observe(this, feedArticleListData -> {
            mFeedArticleDataList.clear();
            mFeedArticleDataList.addAll(feedArticleListData.getDatas());
            articleListAdapter.setList(mFeedArticleDataList);
        });
        mViewModel.getArticle();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }


}
