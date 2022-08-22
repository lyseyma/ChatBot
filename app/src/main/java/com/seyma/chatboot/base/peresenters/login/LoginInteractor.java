package com.seyma.chatboot.base.peresenters.login;

import com.seyma.chatboot.base.peresenters.BaseInteractor;

/**
 * @author AOS-SEYMA
 * @since 11-Aug-22
 */
public interface LoginInteractor extends BaseInteractor {
    void login(String username, String password, OnLoginCompleteListener onLoginCompleteListener);
}
