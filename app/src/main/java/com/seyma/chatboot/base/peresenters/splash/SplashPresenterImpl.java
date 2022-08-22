package com.seyma.chatboot.base.peresenters.splash;

import android.content.Context;

import com.seyma.chatboot.common.utils.UserAuth;
import com.seyma.chatboot.models.User;
import com.seyma.chatboot.views.splash.SplashView;

/**
 * @author AOS-SEYMA
 * @since 12-Aug-22
 */
public class SplashPresenterImpl implements SplashPresenter {

    private Context context;
    private SplashView splashView;
    private SplashInteractor interactor;

    public SplashPresenterImpl(Context context , SplashView view){
        this.context = context;
        this.splashView = view;
        interactor = new SplashInteractorImpl();
    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void goToOnlineState() {
        interactor.updateUserOnlineSateAndFetchUser(UserAuth.getUserID(), true, new OnGetUserCompleteListener() {
            @Override
            public void onGetUserSuccess(User user) {
                UserAuth.saveUser(context , user);
                splashView.completeLoading();
            }

            @Override
            public void onRequestError(String message) {
                splashView.showErrorDialog();
            }
        });

    }
}
