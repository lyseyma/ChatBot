package com.seyma.chatboot.base.peresenters.main;

import com.seyma.chatboot.base.peresenters.OnRequestCompleteListener;
import com.seyma.chatboot.common.utils.UserAuth;

/**
 * @author AOS-SEYMA
 * @since 19-Aug-22
 */
public class ApplicationPresenterImpl implements ApplicationPresenter{

    private ApplicationPresenter applicationPresenter;

    public ApplicationPresenterImpl(){
        this.applicationPresenter = new ApplicationPresenterImpl();
    }
    @Override
    public void onViewDestroy() {
        applicationPresenter.onViewDestroy();
    }

    @Override
    public void changeOnlineState(boolean isOnline, OnRequestCompleteListener listener) {
        String userID = UserAuth.getUserID();
        if (userID != null){

        }
    }
}
