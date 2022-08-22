package com.seyma.chatboot.base.peresenters.main;

import com.seyma.chatboot.base.peresenters.BaseInteractor;
import com.seyma.chatboot.base.peresenters.OnRequestCompleteListener;

/**
 * @author AOS-SEYMA
 * @since 19-Aug-22
 */
public interface ApplicationInteractor extends BaseInteractor {

    void updateUserOnlineState(String userID , boolean isOnline , OnRequestCompleteListener listener);
}
