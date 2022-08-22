package com.seyma.chatboot.views.main.home;

import com.seyma.chatboot.common.adapter.recycler_view_adapter.View.UserInfoChatRooms;
import com.seyma.chatboot.models.ChatRoom;
import com.seyma.chatboot.models.ChatRoomInfo;

import java.util.List;
import java.util.Map;

/**
 * @author AOS-SEYMA
 * @since 19-Aug-22
 */
public interface HomeView {

    void showRefreshingProgress();
    void hideRefreshingProgress();
    void enableRefreshing(boolean enable);
    void refreshChatRooms(Map<String , UserInfoChatRooms> userInfoChatRoomsMap , List<ChatRoom> chatRooms);

    void showLoadingMoreProgress();
    void hideLoadingMoreProgress();
    void enableLoadingMore(boolean enable);
    void addMoreChatRooms(Map<String , UserInfoChatRooms> userInfoChatRoomsMap , List<ChatRoom> chatRooms);
    void updateChatRoom(ChatRoomInfo chatRoomInfo);
}
