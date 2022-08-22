package com.seyma.chatboot.base.peresenters.main;

import com.google.firebase.firestore.FirebaseFirestore;
import com.seyma.chatboot.base.peresenters.OnRequestCompleteListener;
import com.seyma.chatboot.common.Constants;
import com.seyma.chatboot.models.User;

/**
 * @author AOS-SEYMA
 * @since 19-Aug-22
 */
public class ApplicationInteractorImpl implements ApplicationInteractor{
    @Override
    public void onViewDestroy() {

    }

    @Override
    public void updateUserOnlineState(String userID, boolean isOnline, OnRequestCompleteListener listener) {
        FirebaseFirestore.getInstance()
                .collection(Constants.USERS_COLLECTION)
                .document(userID)
                .update(User.IS_ONLINE,isOnline)
                .addOnSuccessListener(aVoid ->{
                    if (listener != null){
                        listener.onRequestSuccess();
                    }
                })
                .addOnFailureListener(e ->{
                    if (listener != null){
                        listener.onRequestError(e.getMessage());
                    }
                });
    }
}
