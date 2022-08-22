package com.seyma.chatboot.views.splash;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.databinding.ViewDataBinding;

import com.seyma.chatboot.BR;
import com.seyma.chatboot.R;
import com.seyma.chatboot.base.activity.BaseActivity;
import com.seyma.chatboot.base.peresenters.splash.SplashPresenter;
import com.seyma.chatboot.base.peresenters.splash.SplashPresenterImpl;
import com.seyma.chatboot.common.Constants;
import com.seyma.chatboot.common.utils.UserAuth;
import com.seyma.chatboot.common.utils.Utils;
import com.seyma.chatboot.databinding.ActivitySplashBinding;
import com.seyma.chatboot.views.login.LoginActivity;
import com.seyma.chatboot.views.main.MainActivity;


public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashView {

    private ActivitySplashBinding binding;

    private volatile boolean isRequestSuccess = false;
    private AsyncTask<Void, Integer, Void> splashTimer;


    @Override
    protected int getLayoutResources() {
        return R.layout.activity_splash;
    }


    @Override
    protected void initVariables(Bundle savedInstanceState , ViewDataBinding dataBinding) {
        binding = (ActivitySplashBinding) dataBinding;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (UserAuth.isUserLoggedIn()) {
            if(Utils.isInternetAvailable(this)) {
                getPresenter().goToOnlineState();
            } else {
                completeLoading();
            }
            splashTimer = new SplashTimer().execute();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected SplashPresenter initPresenter() {
        return new SplashPresenterImpl(this , this);
    }


    @Override
    public void completeLoading() {
        isRequestSuccess = true;
    }

    @Override
    public void showErrorDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.error_occurred)
                .setMessage(R.string.unexpected_error_occurred)
                .setCancelable(false)
                .setPositiveButton(R.string.retry, (dialogInterface, i) -> {
                    getPresenter().goToOnlineState();
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(splashTimer != null && !splashTimer.isCancelled()) {
            splashTimer.cancel(true);
            splashTimer = null;
        }
    }

    private class SplashTimer extends AsyncTask<Void, Integer, Void> {
        @Override
        protected void onProgressUpdate(Integer... values) {
            binding.loadingBar.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            long millisPerProgress = Constants.TIME_SPLASH_SCREEN / 100;
            int progress = 0;
            try {
                while (progress <= 80) {
                    progress++;
                    publishProgress(progress);
                    Thread.sleep(millisPerProgress);
                }
                while (!isRequestSuccess) {
                }
                while (progress <= 100) {
                    progress++;
                    publishProgress(progress);
                    Thread.sleep(millisPerProgress);
                }
            } catch (InterruptedException ignored) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}