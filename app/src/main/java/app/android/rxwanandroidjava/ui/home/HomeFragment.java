package app.android.rxwanandroidjava.ui.home;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import app.android.rxwanandroidjava.R;
import app.android.rxwanandroidjava.common.base.BaseFragment;
import app.android.rxwanandroidjava.common.network.BaseData;
import app.android.rxwanandroidjava.common.network.RetrofitManager;
import app.android.rxwanandroidjava.ui.home.adapter.ArticleListAdapter;
import app.android.rxwanandroidjava.ui.home.bean.FeedArticleData;
import app.android.rxwanandroidjava.ui.home.bean.FeedArticleListData;

public class HomeFragment extends BaseFragment {

    private HomeViewModel mViewModel;
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
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        mRecyclerView = view.findViewById(R.id.mRecyclerView);
        mSmartRefreshLayout = view.findViewById(R.id.mSmartRefreshLayout);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);

        mFeedArticleDataList = new ArrayList<FeedArticleData>();
        articleListAdapter = new ArticleListAdapter(getActivity(), mFeedArticleDataList);
        mRecyclerView.setAdapter(articleListAdapter);
    }

    @Override
    protected void initData() {
        getArticle();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

    void getArticle() {

        RetrofitManager.getInstance().enqueue(RetrofitManager.getInstance().getApiConfig().getHomeArticle(0), new RetrofitManager.CallBack() {
            @Override
            public void onSuccess(Object object) {
                BaseData baseData = (BaseData) object;
                FeedArticleListData data = baseData.getData();
                mFeedArticleDataList.clear();
                mFeedArticleDataList.addAll(data.getDatas());
                articleListAdapter.setList(mFeedArticleDataList);
            }

            @Override
            public void onFailure(double errorCode, String errorMsg) {

            }
        });

    }
}
