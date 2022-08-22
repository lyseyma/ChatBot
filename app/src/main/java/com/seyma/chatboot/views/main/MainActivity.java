package com.seyma.chatboot.views.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import com.seyma.chatboot.AppStatusApplication;
import com.seyma.chatboot.R;
import com.seyma.chatboot.base.activity.BaseActivity;
import com.seyma.chatboot.base.activity.BasePresenter;
import com.seyma.chatboot.base.peresenters.OnRequestCompleteListener;
import com.seyma.chatboot.base.peresenters.services.UserChangeListenerService;
import com.seyma.chatboot.common.adapter.view_pager_adapter.HomeFragmentPagerAdapter;
import com.seyma.chatboot.common.custom_view.LoadingDialog;
import com.seyma.chatboot.common.utils.UserAuth;
import com.seyma.chatboot.databinding.ActivityMain2Binding;
import com.seyma.chatboot.views.chat_bot.ChatBotActivity;
import com.seyma.chatboot.views.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NavigationBarView.OnItemSelectedListener {

    private LoadingDialog loadingDialog;

    private ActivityMain2Binding binding;

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_main2;
    }

    @SuppressLint("UseSupportActionBar")
    @Override
    protected void initVariables(Bundle savedInstanceState , ViewDataBinding dataBinding) {

        binding = (ActivityMain2Binding) dataBinding;
        ButterKnife.bind(this);

        setActionBar(binding.toolBar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        binding.bottomNavigation.setOnItemSelectedListener(this);
        binding.bottomNavigation.setSelectedItemId(R.id.navigation_home);

        binding.navView.setNavigationItemSelectedListener(navigationItemSelectedListener);

        loadingDialog = new LoadingDialog(this);

        startService(new Intent(this, UserChangeListenerService.class));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, UserChangeListenerService.class));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        HomeFragmentPagerAdapter homeFragmentPagerAdapter = new HomeFragmentPagerAdapter(this);
        binding.viewPager.setAdapter(homeFragmentPagerAdapter);

        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.bottomNavigation.setSelectedItemId(HomeFragmentPagerAdapter.getMenuIDByPosition(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            binding.drawerLayout.openDrawer(Gravity.LEFT);
        }
        return super.onOptionsItemSelected(item);
    }

    private NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = item -> {

        switch (item.getItemId()) {
            case R.id.nav_home: {
                binding.viewPager.setCurrentItem(HomeFragmentPagerAdapter.HOME_FRAGMENT_POSITION);
                binding.drawerLayout.closeDrawer(Gravity.LEFT);
                return true;
            }

            case R.id.nav_friends: {
                binding.viewPager.setCurrentItem(HomeFragmentPagerAdapter.FRIENDS_FRAGMENT_POSITION);
                binding.drawerLayout.closeDrawer(Gravity.LEFT);
                return true;
            }

            case R.id.nav_profile: {
                binding.viewPager.setCurrentItem(HomeFragmentPagerAdapter.PROFILE_FRAGMENT_POSITION);
                binding.drawerLayout.closeDrawer(Gravity.LEFT);
                return true;
            }

            case R.id.nav_chat_bot: {
                Intent intent = new Intent(MainActivity.this, ChatBotActivity.class);
                startActivity(intent);
                return false;
            }

            case R.id.nav_logout: {
                logout();
                return false;
            }

            default: {
                return false;
            }
        }
    };

    private void logout(){
        loadingDialog.show();

        ((AppStatusApplication) getApplication()).getApplicationPresenter()
                .changeOnlineState(false, new OnRequestCompleteListener() {
                    @Override
                    public void onRequestSuccess() {
                        FirebaseMessaging.getInstance().unsubscribeFromTopic(UserAuth.getUserID());
                        FirebaseAuth.getInstance().signOut();
                        UserAuth.saveUser(MainActivity.this, null);
                        startActivity(new Intent(MainActivity.this , LoginActivity.class));
                        loadingDialog.dismiss();
                        finish();
                    }

                    @Override
                    public void onRequestError(String message) {
                        loadingDialog.dismiss();
                        Toast.makeText(MainActivity.this, R.string.logout_failure , Toast.LENGTH_LONG).show();
                    }
                });
    }

}