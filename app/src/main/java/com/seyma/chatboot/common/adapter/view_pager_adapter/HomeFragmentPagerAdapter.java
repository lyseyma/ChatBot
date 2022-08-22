package com.seyma.chatboot.common.adapter.view_pager_adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.seyma.chatboot.R;
import com.seyma.chatboot.views.main.account.AccountFragment;
import com.seyma.chatboot.views.main.friends.FriendsFragment;
import com.seyma.chatboot.views.main.home.HomeFragment;

/**
 * @author AOS-SEYMA
 * @since 18-Aug-22
 */
public class HomeFragmentPagerAdapter extends FragmentStateAdapter {

    public static final int HOME_FRAGMENT_POSITION = 0;
    public static final int FRIENDS_FRAGMENT_POSITION = 1;
    public static final int PROFILE_FRAGMENT_POSITION = 2;

    private Fragment[] fragments;

    public HomeFragmentPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        fragments = new Fragment[3];

        fragments[HOME_FRAGMENT_POSITION] = new HomeFragment();
        fragments[FRIENDS_FRAGMENT_POSITION] = new FriendsFragment();
        fragments[PROFILE_FRAGMENT_POSITION] = new AccountFragment();

    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }

    public static int getMenuIDByPosition(int position){
        switch (position){
            case FRIENDS_FRAGMENT_POSITION:{
                return R.id.navigation_friends;
            }
            case PROFILE_FRAGMENT_POSITION:{
                return R.id.navigation_account;
            }
            default:{
                return R.id.navigation_home;
            }
        }
    }
}
