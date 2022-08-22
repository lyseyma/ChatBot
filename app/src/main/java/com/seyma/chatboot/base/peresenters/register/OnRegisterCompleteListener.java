package com.seyma.chatboot.base.peresenters.register;

import com.seyma.chatboot.base.peresenters.BaseRequestListener;

/**
 * @author AOS-SEYMA
 * @since 12-Aug-22
 */
public interface OnRegisterCompleteListener extends BaseRequestListener {
    void onRegisterSuccess(String email);
    void onEmailExist();
    void onPasswordWeek();

}
