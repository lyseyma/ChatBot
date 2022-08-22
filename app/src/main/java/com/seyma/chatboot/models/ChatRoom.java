package com.seyma.chatboot.models;

import android.content.Context;

import com.seyma.chatboot.R;
import com.seyma.chatboot.common.utils.UserAuth;
import com.seyma.chatboot.models.message_models.BaseMessage;
import com.seyma.chatboot.models.message_models.ImageMessage;
import com.seyma.chatboot.models.message_models.TextMessage;

import java.util.Map;

/**
 * @author AOS-SEYMA
 * @since 19-Aug-22
 */
public class ChatRoom {
    private String roomID;
    private Map<String , UserInfo> friendsInfo;
    private String lastMessage;
    private boolean isOwnedMessage;
    private boolean isSeen;

    public ChatRoom(Context context , ChatRoomInfo  chatRoomInfo , Map<String , Object> lastMessageMap){
        update(context, chatRoomInfo, lastMessageMap);

    }

    public void update(Context context , ChatRoomInfo chatRoom , Map<String , Object> lastMessageMp){
        setRoomID(chatRoom.getId());
        setFriendsInfo(chatRoom.getUsersInfo());
        setLastMessage(context, lastMessageMp);


    }


    public void setLastMessage(Context context , Map<String , Object> lastMessageMap){
        String userID = UserAuth.getUserID();
        String ownerID = (String) lastMessageMap.get(BaseMessage.OWNER_ID);
        this.isOwnedMessage = ownerID.equals(userID);
        Map<String , Boolean> seenBy = (Map<String, Boolean>) lastMessageMap.get(BaseMessage.SEEN_BY);
        this.isSeen = (seenBy != null) && seenBy.containsKey(userID);
        String messageType = (String) lastMessageMap.get(BaseMessage.TYPE);
        String currentUserID = UserAuth.getUserID();
        switch (messageType){
            case BaseMessage.TEXT_MESSAGE:
                String sender;
                if (ownerID.equals(currentUserID)){
                    sender = context.getString(R.string.you);
                }else {
                    UserInfo friendInfo = friendsInfo.get(ownerID);
                    if (friendInfo == null)return;
                    sender = friendInfo.getFirstName();
                }
                this.lastMessage = sender = ": " + lastMessageMap.get(TextMessage.MESSAGE);
                break;
            case BaseMessage.IMAGE_MESSAGE:

                int imageCount = ((Map) lastMessageMap.get(ImageMessage.IMAGES)).size();
                if (ownerID.equals(currentUserID)){
                    this.lastMessage = context.getString(R.string.you_have_sent);
                }else {
                    UserInfo friendInfo = friendsInfo.get(ownerID);
                    if (friendInfo == null) return;
                    this.lastMessage = friendInfo.getFirstName() + " " + context.getString(R.string.has_sent);
                }
                this.lastMessage += " "+ ((imageCount == 1) ? context.getString(R.string.an_image) : (imageCount + " " + context.getString(R.string.images)));

                break;

            case BaseMessage.EMOJI_MESSAGE:
                if (ownerID.equalsIgnoreCase(currentUserID)){
                    this.lastMessage = context.getString(R.string.you_have_sent_an_emoji);
                }else {
                    UserInfo friendInfo = friendsInfo.get(ownerID);
                    if (friendInfo == null) return;
                    this.lastMessage = friendInfo.getFirstName() + " " + context.getString(R.string.you_have_sent_an_emoji);
                }
                break;
            case BaseMessage.LOCATION_MESSAGE:
                if (ownerID.equals(currentUserID)){
                    this.lastMessage = context.getString(R.string.you_have_sent_a_location);
                }else {
                    UserInfo friendInfo = friendsInfo.get(ownerID);
                    if (friendInfo == null) return;
                    this.lastMessage = friendInfo.getFirstName() + "" + context.getString(R.string.you_have_sent_a_location);
                }
                break;
            default:
                break;

        }


    }

    public String getRoomID(){return roomID;}

    public void setRoomID(String roomID){this.roomID = roomID;}

    public Map<String, UserInfo> getFriendsInfo(){return friendsInfo;}

    public void setFriendsInfo(Map<String , UserInfo> userInfoMap){
        userInfoMap.remove(UserAuth.getUserID());
        this.friendsInfo = userInfoMap;
    }

    public String getLastMessage(){ return lastMessage;}

    public void setLastMessage(String lastMessage){ this.lastMessage = lastMessage;}

    public boolean isOwnedMessage(){ return isOwnedMessage;}

    public void setOwnedMessage(boolean ownedMessage){ isOwnedMessage = ownedMessage; }

    public boolean isSeen(){ return isSeen;}

    public void setSeen(boolean seen){isSeen = seen;}

    public boolean isOnline(){
        for(Map.Entry<String , UserInfo> friendInfoEntry :  friendsInfo.entrySet()){
            if (friendInfoEntry.getValue().getIsOnline()){
                return true;
            }
        }
        return false;
    }


}
