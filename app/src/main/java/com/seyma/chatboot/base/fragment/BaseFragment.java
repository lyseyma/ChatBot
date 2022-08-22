package com.seyma.chatboot.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.seyma.chatboot.base.activity.BasePresenter;


/**
 * @author AOS-SEYMA
 * @since 19-Aug-22
 */
public abstract class BaseFragment <T extends BasePresenter> extends Fragment {

    private T presenter;

    protected abstract int getLayoutResources();

    protected abstract void initVariable(Bundle saveInstancesState , View rootView);

    protected abstract void initData(Bundle saveInstanceState);

    protected abstract T initPresenter();

    public T getPresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutResources() , container , false);
        presenter = initPresenter();
        initVariable(savedInstanceState , rootView);
        initData(savedInstanceState);
        return rootView;
    }
}
