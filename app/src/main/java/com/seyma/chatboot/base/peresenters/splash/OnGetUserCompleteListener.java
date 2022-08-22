package com.seyma.chatboot.base.peresenters.splash;


import com.seyma.chatboot.base.peresenters.BaseRequestListener;
import com.seyma.chatboot.models.User;

public interface OnGetUserCompleteListener extends BaseRequestListener {
    void onGetUserSuccess(User user);
}
