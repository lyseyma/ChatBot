package com.seyma.chatboot.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author AOS-SEYMA
 * @since 19-Aug-22
 */
public class ChatRoomInfo implements Serializable {

    public static final String USERS_INFO       = "userInfo";
    public static final String VISIT_STATES     = "visitStates";
    public static final String TYPING_STATES    = "typingStates";
    public static final String USER_SETTINGS    = "userSettings";
    public static final String LAST_MESSAGE     = "lastMessage";
    public static final String MESSAGES         = "message";

    private String id;
    private Map<String , Object> lastMessage;
    private Map<String , UserInfo> usersInfo;

    public ChatRoomInfo(){

    }

    public ChatRoomInfo(String id , List<UserInfo> usersInfo){
        this.id  = id;
        this.usersInfo = new HashMap<>(usersInfo.size());
        for (UserInfo userInfo : usersInfo){
            this.usersInfo.put(userInfo.getId() , userInfo);
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Map<String, Object> lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Map<String, UserInfo> getUsersInfo() {
        return usersInfo;
    }

    public void setUsersInfo(Map<String, UserInfo> usersInfo) {
        this.usersInfo = usersInfo;
    }
}
