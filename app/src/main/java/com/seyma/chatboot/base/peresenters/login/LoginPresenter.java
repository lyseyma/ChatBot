package com.seyma.chatboot.base.peresenters.login;


import com.seyma.chatboot.base.activity.BasePresenter;

/**
 * @author AOS-SEYMA
 * @since 11-Aug-22
 */
public interface LoginPresenter extends BasePresenter {
    void validateEmailAndPassword(String email, String password);
}
