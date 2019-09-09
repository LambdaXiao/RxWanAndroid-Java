package app.android.rxwanandroidjava.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import app.android.rxwanandroidjava.R;
import app.android.rxwanandroidjava.common.base.BaseFragment;
import app.android.rxwanandroidjava.ui.home.adapter.ArticleListAdapter;
import app.android.rxwanandroidjava.ui.home.bean.BannerBean;
import app.android.rxwanandroidjava.ui.home.bean.FeedArticleBean;
import app.android.rxwanandroidjava.utils.PhoneUtils;
import app.android.rxwanandroidjava.widget.RecyclerViewBanner;


public class HomeFragment extends BaseFragment {

    private HomeViewModel mViewModel;
    private TextView mTitle;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;
    private RecyclerViewBanner rvbanner;
    private ArticleListAdapter articleListAdapter;
    private List<FeedArticleBean> mFeedArticleDataList;
    private List<BannerBean> mBannerList;
    private int pagenum = 0;//页面页数下标
    private boolean isRefresh = false;
    private boolean isLoadMore = false;

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
        rvbanner = view.findViewById(R.id.rv_banner);
        //banner
        rvbanner.setIndicatorInterval(5000);
        mBannerList = new ArrayList<>();
        rvbanner.setOnSwitchRvBannerListener(new RecyclerViewBanner.OnSwitchRvBannerListener() {
            @Override
            public void switchBanner(int position, AppCompatImageView bannerView) {
                if (mActivity != null) {
                    //加入判断防止报 You cannot start a load for a destroyed activity
                    Glide.with(mActivity)
                            .load(mBannerList.get(position).getImagePath())
                            .centerCrop()
                            .override(PhoneUtils.getScreenWidth(mActivity), PhoneUtils.getScreenWidth(mActivity) * 444 / 800)
                            .into(bannerView);
                }
            }
        });

        rvbanner.setOnRvBannerClickListener(new RecyclerViewBanner.OnRvBannerClickListener() {
            @Override
            public void onClick(int position) {
//                Banner banner = banners.get(position);
//                if (banner != null) {
//                    try {
//                        AnalyzeUrlHelp.analyze(mActivity, banner.getUrl(), banner.getTitle(), banner.getDes());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mSmartRefreshLayout.setEnableLoadMore(true);
        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isRefresh = false;
                isLoadMore = true;
                pagenum++;
                mViewModel.getArticle(pagenum);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isRefresh = true;
                isLoadMore = false;
                pagenum = 0;
                mViewModel.getArticle(pagenum);
            }
        });
    }

    @Override
    protected void initData() {

        mViewModel.getBannerData().observe(this, bannerBeans -> {
            mBannerList.clear();
            mBannerList.addAll(bannerBeans);
            rvbanner.setRvBannerData(mBannerList);
        });

        mFeedArticleDataList = new ArrayList<FeedArticleBean>();
        articleListAdapter = new ArticleListAdapter(getActivity(), mFeedArticleDataList);
        mRecyclerView.setAdapter(articleListAdapter);
        //数据变化观察
        mViewModel.getLiveData().observe(this, feedArticleListData -> {
            if (isRefresh) {
                mFeedArticleDataList.clear();
            }
            mFeedArticleDataList.addAll(feedArticleListData.getDatas());
            articleListAdapter.setList(mFeedArticleDataList);
            mSmartRefreshLayout.finishRefresh();
            mSmartRefreshLayout.finishLoadMore();
            isRefresh = false;
            isLoadMore = false;
        });

        mViewModel.getBanner();
        mViewModel.getArticle(pagenum);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


}
