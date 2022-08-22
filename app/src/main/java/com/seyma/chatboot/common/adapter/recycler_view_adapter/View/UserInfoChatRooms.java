package com.seyma.chatboot.common.adapter.recycler_view_adapter.View;

import com.seyma.chatboot.models.UserInfo;

import java.util.HashSet;
import java.util.Set;

/**
 * @author AOS-SEYMA
 * @since 19-Aug-22
 */
public class UserInfoChatRooms {
    private UserInfo userInfo;
    private Set<String> roomID;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Set<String> getRoomID() {
        return roomID;
    }

    public void setRoomID(Set<String> roomID) {
        this.roomID = roomID;
    }

    public UserInfoChatRooms(UserInfo userInfo) {
        this.userInfo = userInfo;
        this.roomID = new HashSet<>(1);
    }


}
