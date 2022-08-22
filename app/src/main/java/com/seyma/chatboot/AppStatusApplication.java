package com.seyma.chatboot;

import android.app.Application;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.seyma.chatboot.base.peresenters.main.ApplicationPresenter;
import com.seyma.chatboot.base.peresenters.main.ApplicationPresenterImpl;

/**
 * @author AOS-SEYMA
 * @since 19-Aug-22
 */
public class AppStatusApplication extends Application implements LifecycleObserver {
    private ApplicationPresenter applicationPresenter;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationPresenter = new ApplicationPresenterImpl();

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }
    public ApplicationPresenter getApplicationPresenter(){return applicationPresenter;}

    void onAppBackgrounded(){getApplicationPresenter().changeOnlineState(false, null);}

    void onAppForegrounded() {
        getApplicationPresenter().changeOnlineState(true, null);
    }


}
