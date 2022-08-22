package com.seyma.chatboot.base.peresenters.main;

import com.seyma.chatboot.base.activity.BasePresenter;
import com.seyma.chatboot.base.peresenters.OnRequestCompleteListener;

/**
 * @author AOS-SEYMA
 * @since 19-Aug-22
 */
public interface ApplicationPresenter extends BasePresenter {
    void changeOnlineState(boolean isOnline , OnRequestCompleteListener listener);
}
