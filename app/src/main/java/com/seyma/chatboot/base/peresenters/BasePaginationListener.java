package com.seyma.chatboot.base.peresenters;

/**
 * Created by TranThanhTung on 21/03/2018.
 */

public interface BasePaginationListener<T> extends BaseRequestListener {
    void onLastElementFetched(T element, boolean hasNoElementLeft);
}
