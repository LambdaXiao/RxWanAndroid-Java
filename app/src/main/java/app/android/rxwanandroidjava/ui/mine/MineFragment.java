package app.android.rxwanandroidjava.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import app.android.rxwanandroidjava.R;
import app.android.rxwanandroidjava.common.aop.SingleClick;
import app.android.rxwanandroidjava.common.base.BaseFragment;
import app.android.rxwanandroidjava.databinding.MineFragmentBinding;
import app.android.rxwanandroidjava.ui.mine.activity.AboutUserActivity;

public class MineFragment extends BaseFragment {

    private MineFragmentBinding mFragmentBinding;
    private MineViewModel mViewModel;
    private Toolbar mToolbar;
    private TextView mTitle;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.mine_fragment, container, false);

        mToolbar = mFragmentBinding.getRoot().findViewById(R.id.common_toolbar);
        mTitle = mFragmentBinding.getRoot().findViewById(R.id.common_toolbar_title_tv);
        mTitle.setText(getString(R.string.menu_me));
        initToolbar();

        mTitle.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AboutUserActivity.class));
            }
        });

        return mFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MineViewModel.class);
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
