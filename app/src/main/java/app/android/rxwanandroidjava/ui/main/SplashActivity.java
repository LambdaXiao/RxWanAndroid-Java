package app.android.rxwanandroidjava.ui.main;

import android.content.Intent;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;

import app.android.rxwanandroidjava.R;
import app.android.rxwanandroidjava.common.base.BaseActivity;

/**
 * app第一个页面
 */
public class SplashActivity extends BaseActivity {

    LottieAnimationView lottieAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        lottieAnimation = findViewById(R.id.lottieAnimation);

        lottieAnimation.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (lottieAnimation != null) {
            lottieAnimation.cancelAnimation();
        }
    }
}
