package app.android.rxwanandroidjava.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import app.android.rxwanandroidjava.R;
import app.android.rxwanandroidjava.common.base.BaseFragment;
import app.android.rxwanandroidjava.databinding.HomeFragmentBinding;
import app.android.rxwanandroidjava.ui.home.adapter.ArticleListAdapter;
import app.android.rxwanandroidjava.ui.home.bean.BannerBean;
import app.android.rxwanandroidjava.ui.home.bean.FeedArticleBean;
import app.android.rxwanandroidjava.utils.PhoneUtils;
import app.android.rxwanandroidjava.widget.RecyclerViewBanner;


public class HomeFragment extends BaseFragment {

    private HomeFragmentBinding mFragmentBinding;
    private HomeViewModel mViewModel;
    private TextView mTitle;
    private ArticleListAdapter articleListAdapter;
    private List<FeedArticleBean> mFeedArticleDataList;
    private List<BannerBean> mBannerList;
    private int pagenum = 0;//页面页数下标
    private boolean isRefresh = false;
    private boolean isLoadMore = false;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        return mFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        mFragmentBinding.setVm(mViewModel);
        mFragmentBinding.setLifecycleOwner(this);
        initLayout();
        initData();
    }

    protected void initLayout() {
        mTitle = mFragmentBinding.getRoot().findViewById(R.id.common_toolbar_title_tv);
        mTitle.setText(getString(R.string.menu_home));

        //banner
        mBannerList = new ArrayList<>();
        mFragmentBinding.rvBanner.setOnSwitchRvBannerListener(new RecyclerViewBanner.OnSwitchRvBannerListener() {
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

        mFragmentBinding.rvBanner.setOnRvBannerClickListener(new RecyclerViewBanner.OnRvBannerClickListener() {
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

        mFragmentBinding.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFragmentBinding.mRecyclerView.setHasFixedSize(true);
        mFragmentBinding.mSmartRefreshLayout.setEnableLoadMore(true);
        mFragmentBinding.mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isRefresh = false;
                isLoadMore = true;
                pagenum++;
                mViewModel.requestArticleList(pagenum);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isRefresh = true;
                isLoadMore = false;
                pagenum = 0;
                mViewModel.requestArticleList(pagenum);
            }
        });

        mFeedArticleDataList = new ArrayList<FeedArticleBean>();
        articleListAdapter = new ArticleListAdapter(getActivity(), mFeedArticleDataList);
        mFragmentBinding.mRecyclerView.setAdapter(articleListAdapter);
    }

    protected void initData() {

        //Banner数据变化观察
        mViewModel.getBannerData().observe(getViewLifecycleOwner(), bannerBeans -> {
            mBannerList.clear();
            mBannerList.addAll(bannerBeans);
            mFragmentBinding.rvBanner.setRvBannerData(mBannerList);
        });

        //列表数据变化观察
        mViewModel.getArticleList().observe(getViewLifecycleOwner(), feedArticleListData -> {
            if (isRefresh) {
                mFeedArticleDataList.clear();
            }
            mFeedArticleDataList.addAll(feedArticleListData.getDatas());
            articleListAdapter.setList(mFeedArticleDataList);
            mFragmentBinding.mSmartRefreshLayout.finishRefresh();
            mFragmentBinding.mSmartRefreshLayout.finishLoadMore();
            isRefresh = false;
            isLoadMore = false;
        });

        //请求数据
        mViewModel.requestBanner();
        mViewModel.requestArticleList(pagenum);
    }
}
