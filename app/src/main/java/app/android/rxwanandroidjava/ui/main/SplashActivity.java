package app.android.rxwanandroidjava.ui.main;

import android.content.Intent;

import com.airbnb.lottie.LottieAnimationView;

import app.android.rxwanandroidjava.R;
import app.android.rxwanandroidjava.common.base.BaseActivity;

/**
 * app第一个页面
 */
public class SplashActivity extends BaseActivity {

    LottieAnimationView lottieAnimation;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        lottieAnimation = findViewById(R.id.lottieAnimation);

        lottieAnimation.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (lottieAnimation != null) {
            lottieAnimation.cancelAnimation();
        }
    }
}
