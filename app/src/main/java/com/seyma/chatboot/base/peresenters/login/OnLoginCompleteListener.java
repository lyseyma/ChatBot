package com.seyma.chatboot.base.peresenters.login;

import com.seyma.chatboot.base.peresenters.BaseRequestListener;
import com.seyma.chatboot.models.User;

public interface OnLoginCompleteListener extends BaseRequestListener {
    void onLoginSuccess(User user);
    void onWrongEmailOrPassword();
}
