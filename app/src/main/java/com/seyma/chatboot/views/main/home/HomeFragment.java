package com.seyma.chatboot.views.main.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.View;

import com.seyma.chatboot.R;
import com.seyma.chatboot.base.fragment.BaseFragment;
import com.seyma.chatboot.base.peresenters.main.home.HomePresenter;
import com.seyma.chatboot.common.adapter.recycler_view_adapter.View.UserInfoChatRooms;
import com.seyma.chatboot.models.ChatRoom;
import com.seyma.chatboot.models.ChatRoomInfo;

import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView {


    @Override
    protected int getLayoutResources() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initVariable(Bundle saveInstancesState, View rootView) {

    }

    @Override
    protected void initData(Bundle saveInstanceState) {

    }

    @Override
    protected HomePresenter initPresenter() {
        return null;
    }

    @Override
    public void showRefreshingProgress() {

    }

    @Override
    public void hideRefreshingProgress() {

    }

    @Override
    public void enableRefreshing(boolean enable) {

    }

    @Override
    public void refreshChatRooms(Map<String, UserInfoChatRooms> userInfoChatRoomsMap, List<ChatRoom> chatRooms) {

    }

    @Override
    public void showLoadingMoreProgress() {

    }

    @Override
    public void hideLoadingMoreProgress() {

    }

    @Override
    public void enableLoadingMore(boolean enable) {

    }

    @Override
    public void addMoreChatRooms(Map<String, UserInfoChatRooms> userInfoChatRoomsMap, List<ChatRoom> chatRooms) {

    }

    @Override
    public void updateChatRoom(ChatRoomInfo chatRoomInfo) {

    }
}