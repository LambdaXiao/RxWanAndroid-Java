package app.android.rxwanandroidjava.ui.mine.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.transition.ChangeBounds;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import app.android.rxwanandroidjava.R;
import app.android.rxwanandroidjava.common.base.BaseActivity;
import app.android.rxwanandroidjava.utils.CommonUtils;

/**
 * 登录注册页面
 */
public class SignUpLoginActivity extends BaseActivity {

    ImageView backImg;
    EditText signupUsername;
    EditText signupPassword;
    EditText signupPassword2;
    TextView signUp;
    RelativeLayout relative;
    EditText loginUsername;
    EditText loginPassword;
    TextView login;
    RelativeLayout relative2;
    LinearLayout mainLinear;
    FrameLayout mainFrame;
    ImageView closeImg;
    View view1;
    View view2;
    View view3;
    View view4;
    View view5;

    ImageView logo;
    LinearLayout.LayoutParams params, params2;
    FrameLayout.LayoutParams params3;
    ObjectAnimator animator2, animator1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_login);
        backImg = findViewById(R.id.backImg);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        signupPassword2 = findViewById(R.id.signup_password2);
        signUp = findViewById(R.id.signUp);
        relative = findViewById(R.id.relative);
        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        login = findViewById(R.id.login);
        relative2 = findViewById(R.id.relative2);
        mainLinear = findViewById(R.id.mainLinear);
        mainFrame = findViewById(R.id.mainFrame);
        closeImg = findViewById(R.id.closeImg);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        view4 = findViewById(R.id.view4);
        view5 = findViewById(R.id.view5);
        relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (params.weight != 4.25) {
                    expandSignUp();
                }
            }
        });
        relative2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (params2.weight != 4.25) {
                    expandLogin();
                }
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (params.weight != 4.25) {
                    expandSignUp();
                } else {
                    //todo 注册
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (params2.weight != 4.25) {
                    expandLogin();
                } else {
                    //todo 登录
                }
            }
        });
        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        params2 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        params3 = new FrameLayout.LayoutParams(CommonUtils.dp2px(50), CommonUtils.dp2px(50));

        logo = new ImageView(this);
        logo.setImageResource(R.mipmap.ic_launcher);
        logo.setLayoutParams(params3);

        relative.post(new Runnable() {
            @Override
            public void run() {

                logo.setX((relative2.getRight() / 2));
                logo.setY(CommonUtils.dp2px(50));
                mainFrame.addView(logo);
            }
        });

        params.weight = (float) 0.75;
        params2.weight = (float) 4.25;

        mainLinear.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Rect r = new Rect();
                mainLinear.getWindowVisibleDisplayFrame(r);
                int screenHeight = mainFrame.getRootView().getHeight();

                int keypadHeight = screenHeight - r.bottom;

                if (keypadHeight > screenHeight * 0.15) {
                    // keyboard is opened
                    if (params.weight == 4.25) {
                        animator1 = ObjectAnimator.ofFloat(backImg, "scaleX", (float) 1.95);
                        animator2 = ObjectAnimator.ofFloat(backImg, "scaleY", (float) 1.95);
                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(animator1, animator2);
                        set.setDuration(1000);
                        set.start();
                    } else {
                        animator1 = ObjectAnimator.ofFloat(backImg, "scaleX", (float) 1.75);
                        animator2 = ObjectAnimator.ofFloat(backImg, "scaleY", (float) 1.75);
                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(animator1, animator2);
                        set.setDuration(500);
                        set.start();
                    }
                } else {
                    // keyboard is closed
                    animator1 = ObjectAnimator.ofFloat(backImg, "scaleX", 3);
                    animator2 = ObjectAnimator.ofFloat(backImg, "scaleY", 3);
                    AnimatorSet set = new AnimatorSet();
                    set.playTogether(animator1, animator2);
                    set.setDuration(500);
                    set.start();
                }
            }
        });
    }

    /**
     * 展开注册页面
     */
    private void expandSignUp() {
        signupUsername.setVisibility(View.VISIBLE);
        signupPassword.setVisibility(View.VISIBLE);
        signupPassword2.setVisibility(View.VISIBLE);
        view1.setVisibility(View.VISIBLE);
        view2.setVisibility(View.VISIBLE);
        view3.setVisibility(View.VISIBLE);

        final ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(1000);
        bounds.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

                ObjectAnimator animator1 = ObjectAnimator.ofFloat(logo, "x", relative2.getRight() / 2 - relative.getRight());

                ObjectAnimator animator2 = ObjectAnimator.ofFloat(signupUsername, "alpha", 0, 1);
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(signupPassword, "alpha", 0, 1);
                ObjectAnimator animator4 = ObjectAnimator.ofFloat(signupPassword2, "alpha", 0, 1);
                ObjectAnimator animator5 = ObjectAnimator.ofFloat(view1, "alpha", 0, 1);
                ObjectAnimator animator6 = ObjectAnimator.ofFloat(view2, "alpha", 0, 1);
                ObjectAnimator animator7 = ObjectAnimator.ofFloat(view3, "alpha", 0, 1);

                ObjectAnimator animator8 = ObjectAnimator.ofFloat(signUp, "scaleX", 2);
                ObjectAnimator animator9 = ObjectAnimator.ofFloat(signUp, "scaleY", 2);
                ObjectAnimator animator10 = ObjectAnimator.ofFloat(signUp, "y", login.getY());
                ObjectAnimator animator11 = ObjectAnimator.ofFloat(signUp, "translationX", mainLinear.getWidth() / 2 - relative2.getWidth() / 2 - signUp.getWidth());
                ObjectAnimator animator12 = ObjectAnimator.ofFloat(signUp, "rotation", 0);

                ObjectAnimator animator13 = ObjectAnimator.ofFloat(loginUsername, "alpha", 1, 0);
                ObjectAnimator animator14 = ObjectAnimator.ofFloat(loginPassword, "alpha", 1, 0);
                ObjectAnimator animator15 = ObjectAnimator.ofFloat(view4, "alpha", 1, 0);
                ObjectAnimator animator16 = ObjectAnimator.ofFloat(view5, "alpha", 1, 0);

                ObjectAnimator animator17 = ObjectAnimator.ofFloat(login, "rotation", 90);
                ObjectAnimator animator18 = ObjectAnimator.ofFloat(login, "y", relative2.getHeight() / 2);
                ObjectAnimator animator19 = ObjectAnimator.ofFloat(login, "scaleX", 1);
                ObjectAnimator animator20 = ObjectAnimator.ofFloat(login, "scaleY", 1);

                AnimatorSet set = new AnimatorSet();
                set.playTogether(animator1, animator2, animator3, animator4, animator5, animator6, animator7,
                        animator8, animator9, animator10, animator11, animator12, animator13, animator14, animator15, animator16, animator17, animator18, animator19, animator20);
                set.setDuration(1000).start();
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                loginUsername.setVisibility(View.INVISIBLE);
                loginPassword.setVisibility(View.INVISIBLE);
                view4.setVisibility(View.INVISIBLE);
                view5.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTransitionCancel(Transition transition) {
            }

            @Override
            public void onTransitionPause(Transition transition) {
            }

            @Override
            public void onTransitionResume(Transition transition) {
            }
        });

        TransitionManager.beginDelayedTransition(mainLinear, bounds);

        params.weight = (float) 4.25;
        params2.weight = (float) 0.75;

        relative.setLayoutParams(params);
        relative2.setLayoutParams(params2);

    }

    /**
     * 展开登录页面
     */
    private void expandLogin() {
        loginUsername.setVisibility(View.VISIBLE);
        loginPassword.setVisibility(View.VISIBLE);
        view4.setVisibility(View.VISIBLE);
        view5.setVisibility(View.VISIBLE);

        ChangeBounds bounds2 = new ChangeBounds();
        bounds2.setDuration(1000);
        bounds2.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

                ObjectAnimator animator1 = ObjectAnimator.ofFloat(logo, "x", logo.getX() + relative2.getWidth());

                ObjectAnimator animator2 = ObjectAnimator.ofFloat(loginUsername, "alpha", 0, 1);
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(loginPassword, "alpha", 0, 1);
                ObjectAnimator animator4 = ObjectAnimator.ofFloat(view4, "alpha", 0, 1);
                ObjectAnimator animator5 = ObjectAnimator.ofFloat(view5, "alpha", 0, 1);

                ObjectAnimator animator6 = ObjectAnimator.ofFloat(login, "translationX", mainLinear.getWidth() / 2 - relative.getWidth() / 2 - login.getWidth());
                ObjectAnimator animator7 = ObjectAnimator.ofFloat(login, "rotation", 0);
                ObjectAnimator animator8 = ObjectAnimator.ofFloat(login, "y", signUp.getY());
                ObjectAnimator animator9 = ObjectAnimator.ofFloat(login, "scaleX", 2);
                ObjectAnimator animator10 = ObjectAnimator.ofFloat(login, "scaleY", 2);

                ObjectAnimator animator11 = ObjectAnimator.ofFloat(signupUsername, "alpha", 1, 0);
                ObjectAnimator animator12 = ObjectAnimator.ofFloat(signupPassword2, "alpha", 1, 0);
                ObjectAnimator animator13 = ObjectAnimator.ofFloat(signupPassword, "alpha", 1, 0);
                ObjectAnimator animator14 = ObjectAnimator.ofFloat(view1, "alpha", 1, 0);
                ObjectAnimator animator15 = ObjectAnimator.ofFloat(view2, "alpha", 1, 0);
                ObjectAnimator animator16 = ObjectAnimator.ofFloat(view3, "alpha", 1, 0);

                ObjectAnimator animator17 = ObjectAnimator.ofFloat(signUp, "rotation", 90);
                ObjectAnimator animator18 = ObjectAnimator.ofFloat(signUp, "y", relative.getHeight() / 2);
                ObjectAnimator animator19 = ObjectAnimator.ofFloat(signUp, "scaleX", 1);
                ObjectAnimator animator20 = ObjectAnimator.ofFloat(signUp, "scaleY", 1);

                AnimatorSet set = new AnimatorSet();
                set.playTogether(animator1, animator2, animator3, animator4, animator5, animator6, animator7,
                        animator8, animator9, animator10, animator11, animator12, animator13, animator14, animator15, animator16, animator17, animator18, animator19, animator20);
                set.setDuration(1000).start();
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                signupUsername.setVisibility(View.INVISIBLE);
                signupPassword.setVisibility(View.INVISIBLE);
                signupPassword2.setVisibility(View.INVISIBLE);
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
                view3.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTransitionCancel(Transition transition) {
            }

            @Override
            public void onTransitionPause(Transition transition) {
            }

            @Override
            public void onTransitionResume(Transition transition) {
            }
        });

        TransitionManager.beginDelayedTransition(mainLinear, bounds2);

        params.weight = (float) 0.75;
        params2.weight = (float) 4.25;

        relative.setLayoutParams(params);
        relative2.setLayoutParams(params2);
    }
}
