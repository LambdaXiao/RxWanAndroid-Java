package app.android.rxwanandroidjava.repository.remote.network;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import app.android.rxwanandroidjava.R;

public class LoadingDialog extends AlertDialog {

    private static final int MIN_SHOW_TIME = 500;
    private static final int MIN_DELAY = 500;

    private TextView tvMessage;

    private long mStartTime = -1;
    private boolean mPostedHide = false;
    private final Runnable mDelayedHide = new Runnable() {

        @Override
        public void run() {
            mPostedHide = false;
            mStartTime = -1;
            dismiss();
        }
    };
    private boolean mPostedShow = false;
    private boolean mDismissed = false;
    private final Runnable mDelayedShow = new Runnable() {

        @Override
        public void run() {
            mPostedShow = false;
            if (!mDismissed) {
                mStartTime = System.currentTimeMillis();
                show();
            }
        }
    };
    private Handler mHandler = new Handler();

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.Theme_AppCompat_Dialog);
        View loadView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_loading, null);
        setView(loadView);
        tvMessage = loadView.findViewById(R.id.tv_message);
    }

    public void showDialog() {
        mStartTime = -1;
        mDismissed = false;
        mHandler.removeCallbacks(mDelayedHide);
        mPostedHide = false;
        if (!mPostedShow) {
            mHandler.postDelayed(mDelayedShow, MIN_DELAY);
            mPostedShow = true;
        }
    }

    public void showDialog(String message) {
        tvMessage.setText(message);
        showDialog();
    }

    public void hideDialog() {
        mDismissed = true;
        mHandler.removeCallbacks(mDelayedShow);
        mPostedShow = false;
        long diff = System.currentTimeMillis() - mStartTime;
        if (diff >= MIN_SHOW_TIME || mStartTime == -1) {
            dismiss();
        } else {
            if (!mPostedHide) {
                mHandler.postDelayed(mDelayedHide, MIN_SHOW_TIME - diff);
                mPostedHide = true;
            }
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeCallbacks(mDelayedHide);
        mHandler.removeCallbacks(mDelayedShow);
    }
}