package com.seyma.chatboot.base.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.google.firebase.FirebaseApp;

/**
 * @author AOS-SEYMA
 * @since 11-Aug-22
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    private T presenter;

    protected abstract int getLayoutResources();

    protected abstract void initVariables(Bundle savedInstanceState , ViewDataBinding mViewDataBinding);

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract T initPresenter();

    protected T getPresenter() {
        return presenter;
    }

    protected ViewDataBinding mViewDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        this.mViewDataBinding = DataBindingUtil.setContentView(this,getLayoutResources());

        this.presenter = initPresenter();

        initVariables(savedInstanceState , mViewDataBinding);
        initData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null) {
            presenter.onViewDestroy();
        }
    }
}
