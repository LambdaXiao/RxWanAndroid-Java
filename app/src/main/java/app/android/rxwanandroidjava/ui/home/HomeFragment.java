package app.android.rxwanandroidjava.ui.home;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import app.android.rxwanandroidjava.R;
import app.android.rxwanandroidjava.common.base.BaseDataBindingFragment;
import app.android.rxwanandroidjava.databinding.HomeFragmentBinding;
import app.android.rxwanandroidjava.ui.home.adapter.ArticleListAdapter;
import app.android.rxwanandroidjava.ui.home.bean.BannerBean;
import app.android.rxwanandroidjava.ui.home.bean.FeedArticleBean;
import app.android.rxwanandroidjava.utils.PhoneUtils;
import app.android.rxwanandroidjava.widget.RecyclerViewBanner;


public class HomeFragment extends BaseDataBindingFragment<HomeFragmentBinding> {


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

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initView() {
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        mDataBind.setVm(mViewModel);

        mTitle = mDataBind.getRoot().findViewById(R.id.common_toolbar_title_tv);
        mTitle.setText(getString(R.string.menu_home));

        //banner
        mBannerList = new ArrayList<>();
        mDataBind.rvBanner.setOnSwitchRvBannerListener(new RecyclerViewBanner.OnSwitchRvBannerListener() {
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

        mDataBind.rvBanner.setOnRvBannerClickListener(new RecyclerViewBanner.OnRvBannerClickListener() {
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

        mDataBind.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mDataBind.mRecyclerView.setHasFixedSize(true);
        mDataBind.mSmartRefreshLayout.setEnableLoadMore(true);
        mDataBind.mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isRefresh = false;
                isLoadMore = true;
                pagenum++;
                mViewModel.requestArticleList(getViewLifecycleOwner(), pagenum);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isRefresh = true;
                isLoadMore = false;
                pagenum = 0;
                mViewModel.requestArticleList(getViewLifecycleOwner(), pagenum);
            }
        });

        mFeedArticleDataList = new ArrayList<FeedArticleBean>();
        articleListAdapter = new ArticleListAdapter(getActivity(), mFeedArticleDataList);
        mDataBind.mRecyclerView.setAdapter(articleListAdapter);
    }

    @Override
    protected void initData() {

        //Banner数据变化观察
        mViewModel.getBannerData().observe(getViewLifecycleOwner(), bannerBeans -> {
            mBannerList.clear();
            mBannerList.addAll(bannerBeans);
            mDataBind.rvBanner.setRvBannerData(mBannerList);
        });

        //列表数据变化观察
        mViewModel.getArticleList().observe(getViewLifecycleOwner(), feedArticleListData -> {
            if (isRefresh) {
                mFeedArticleDataList.clear();
            }
            mFeedArticleDataList.addAll(feedArticleListData.getDatas());
            articleListAdapter.setList(mFeedArticleDataList);
            mDataBind.mSmartRefreshLayout.finishRefresh();
            mDataBind.mSmartRefreshLayout.finishLoadMore();
            isRefresh = false;
            isLoadMore = false;
        });

        //请求数据
        mViewModel.requestBanner(getViewLifecycleOwner());
        mViewModel.requestArticleList(getViewLifecycleOwner(), pagenum);
    }
}
