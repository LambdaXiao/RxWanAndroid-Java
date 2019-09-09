package app.android.rxwanandroidjava.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import app.android.rxwanandroidjava.R;
import app.android.rxwanandroidjava.common.aop.SingleClick;
import app.android.rxwanandroidjava.common.base.BaseFragment;
import app.android.rxwanandroidjava.ui.mine.activity.AboutUserActivity;

public class MineFragment extends BaseFragment {

    private MineViewModel mViewModel;
    private Toolbar mToolbar;
    private TextView mTitle;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.mine_fragment;
    }

    @Override
    protected void initLayout(View view) {

        mToolbar = view.findViewById(R.id.common_toolbar);
        mTitle = view.findViewById(R.id.common_toolbar_title_tv);
        mTitle.setText(getString(R.string.menu_me));
        initToolbar();

        mTitle.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AboutUserActivity.class));
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MineViewModel.class);
        // TODO: Use the ViewModel
    }

    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
        mToolbar.inflateMenu(R.menu.menu_setting);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_setting:
                        //点击设置
                        test();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    @SingleClick
    public void test() {
        int a = 6;
        int dd = 9;
        startActivity(new Intent(getActivity(), AboutUserActivity.class));
    }
}
