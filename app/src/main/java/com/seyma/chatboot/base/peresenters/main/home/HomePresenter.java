package com.seyma.chatboot.base.peresenters.main.home;


import com.seyma.chatboot.base.activity.BasePresenter;

/**
 * @author AOS-SEYMA
 * @since 19-Aug-22
 */
public interface HomePresenter extends BasePresenter {
    void refreshChatRooms();
    void loadMoreChatRooms();
    void bindServices();
    void unbindServices();
}
