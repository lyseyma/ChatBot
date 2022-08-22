package com.seyma.chatboot.base.peresenters.register;

import com.seyma.chatboot.base.activity.BasePresenter;

/**
 * @author AOS-SEYMA
 * @since 12-Aug-22
 */
public interface RegisterPresenter extends BasePresenter {
    void validateRegisterForm(String firstName, String lastName , String email , String password , String confirmPassword);
}
