package app.android.rxwanandroidjava.ui.mine.activity;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.AppBarLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import app.android.rxwanandroidjava.R;
import app.android.rxwanandroidjava.common.base.BaseDataBindingActivity;
import app.android.rxwanandroidjava.databinding.ActivityAboutUserBinding;
import app.android.rxwanandroidjava.utils.StatusBarUtil;
import app.android.rxwanandroidjava.widget.ElasticOutInterpolator;

/**
 * 关于我们
 */

public class AboutUserActivity extends BaseDataBindingActivity<ActivityAboutUserBinding> {


    private View.OnClickListener mThemeListener;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_user;
    }

    @Override
    protected void initView() {

        setSupportActionBar(mDataBind.aboutUsToolbar);
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, mDataBind.aboutUsToolbar);
        mDataBind.aboutUsToolbar.setNavigationOnClickListener(v -> onBackPressed());

        showAboutContent();
        setSmartRefreshLayout();

        //进入界面时自动刷新
        mDataBind.aboutUsRefreshLayout.autoRefresh();

        //点击悬浮按钮时自动刷新
        mDataBind.aboutUsFab.setOnClickListener(v -> mDataBind.aboutUsRefreshLayout.autoRefresh());

        //监听 AppBarLayout 的关闭和开启 给 FlyView（纸飞机） 和 ActionButton 设置关闭隐藏动画
        mDataBind.aboutUsAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean misAppbarExpand = true;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int scrollRange = appBarLayout.getTotalScrollRange();
                float fraction = 1f * (scrollRange + verticalOffset) / scrollRange;
                double minFraction = 0.1;
                double maxFraction = 0.8;
                if (mDataBind.aboutUsContent == null || mDataBind.aboutUsFab == null || mDataBind.aboutUsFlyView == null) {
                    return;
                }
                if (fraction < minFraction && misAppbarExpand) {
                    misAppbarExpand = false;
                    mDataBind.aboutUsFab.animate().scaleX(0).scaleY(0);
                    mDataBind.aboutUsFlyView.animate().scaleX(0).scaleY(0);
                    ValueAnimator animator = ValueAnimator.ofInt(mDataBind.aboutUsContent.getPaddingTop(), 0);
                    animator.setDuration(300);
                    animator.addUpdateListener(animation -> {
                        if (mDataBind.aboutUsContent != null) {
                            mDataBind.aboutUsContent.setPadding(0, (int) animation.getAnimatedValue(), 0, 0);
                        }
                    });
                    animator.start();
                }
                if (fraction > maxFraction && !misAppbarExpand) {
                    misAppbarExpand = true;
                    mDataBind.aboutUsFab.animate().scaleX(1).scaleY(1);
                    mDataBind.aboutUsFlyView.animate().scaleX(1).scaleY(1);
                    ValueAnimator animator = ValueAnimator.ofInt(mDataBind.aboutUsContent.getPaddingTop(), DensityUtil.dp2px(25));
                    animator.setDuration(300);
                    animator.addUpdateListener(animation -> {
                        if (mDataBind.aboutUsContent != null) {
                            mDataBind.aboutUsContent.setPadding(0, (int) animation.getAnimatedValue(), 0, 0);
                        }
                    });
                    animator.start();
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void setSmartRefreshLayout() {
        //绑定场景和纸飞机
        mDataBind.aboutUsFlyRefresh.setUp(mDataBind.aboutUsMountain, mDataBind.aboutUsFlyView);
        mDataBind.aboutUsRefreshLayout.setReboundInterpolator(new ElasticOutInterpolator());
        mDataBind.aboutUsRefreshLayout.setReboundDuration(800);
        mDataBind.aboutUsRefreshLayout.setOnRefreshListener(refreshLayout -> {
            updateTheme();
            refreshLayout.finishRefresh(1000);
        });

        //设置让Toolbar和AppBarLayout的滚动同步
        mDataBind.aboutUsRefreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                refreshLayout.finishRefresh(2000);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                refreshLayout.finishLoadMore(3000);
            }

            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                super.onHeaderMoving(header, isDragging, percent, offset, headerHeight, maxDragHeight);
                if (mDataBind.aboutUsAppBar == null || mDataBind.aboutUsToolbar == null) {
                    return;
                }
                mDataBind.aboutUsAppBar.setTranslationY(offset);
                mDataBind.aboutUsToolbar.setTranslationY(-offset);
            }
        });
    }

    private void showAboutContent() {
//        mDataBind.aboutContent.setText(Html.fromHtml(getString(R.string.about_content)));
//        mDataBind.aboutContent.setMovementMethod(LinkMovementMethod.getInstance());
//        try {
//            String versionStr = getString(R.string.app_name)
//                    + " V" + getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
//            mDataBind.aboutVersion.setText(versionStr);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Update appbar theme
     */
    private void updateTheme() {
        if (mThemeListener == null) {
            mThemeListener = new View.OnClickListener() {
                int index = 0;
                int[] ids = new int[]{
                        R.color.colorPrimary,
                        android.R.color.holo_green_light,
                        android.R.color.holo_purple,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_blue_bright,
                };

                @Override
                public void onClick(View v) {
                    int color = ContextCompat.getColor(getApplication(), ids[index % ids.length]);
                    mDataBind.aboutUsRefreshLayout.setPrimaryColors(color);
                    mDataBind.aboutUsFab.setBackgroundColor(color);
                    mDataBind.aboutUsFab.setBackgroundTintList(ColorStateList.valueOf(color));
                    mDataBind.aboutUsToolbarLayout.setContentScrimColor(color);
                    index++;
                }
            };
        }
        mThemeListener.onClick(null);
    }


}
