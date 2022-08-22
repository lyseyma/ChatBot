package com.seyma.chatboot.base.peresenters.register;

import com.seyma.chatboot.base.peresenters.BaseInteractor;

/**
 * @author AOS-SEYMA
 * @since 12-Aug-22
 */
public interface RegisterInteractor extends BaseInteractor {
    void register(String email , String password, String firstName , String lastName , OnRegisterCompleteListener onRegisterCompleteListener);
}
