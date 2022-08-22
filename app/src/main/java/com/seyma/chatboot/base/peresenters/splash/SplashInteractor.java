package com.seyma.chatboot.base.peresenters.splash;

import com.seyma.chatboot.base.peresenters.BaseInteractor;

/**
 * @author AOS-SEYMA
 * @since 12-Aug-22
 */
public interface SplashInteractor extends BaseInteractor {
    void updateUserOnlineSateAndFetchUser(String userID , boolean isOnline , OnGetUserCompleteListener listener);
}
