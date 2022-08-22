package com.seyma.chatboot.base.peresenters.splash;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.seyma.chatboot.common.Constants;
import com.seyma.chatboot.models.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AOS-SEYMA
 * @since 12-Aug-22
 */
public class SplashInteractorImpl implements SplashInteractor {
    @Override
    public void onViewDestroy() {

    }

    @Override
    public void updateUserOnlineSateAndFetchUser(String userID, boolean isOnline, OnGetUserCompleteListener listener) {
        DocumentReference userRef = FirebaseFirestore.getInstance()
                .collection(Constants.USERS_COLLECTION).document(userID);
        FirebaseFirestore.getInstance().runTransaction(transaction -> {
            User user = transaction.get(userRef).toObject(User.class);
            Map<String, Object> onlineStateMap = new HashMap<>();
            onlineStateMap.put(User.IS_ONLINE , true);
            transaction.set(userRef , onlineStateMap , SetOptions.merge());
            return user;
        }).addOnSuccessListener(listener::onGetUserSuccess)
                .addOnFailureListener(e -> listener.onRequestError(e.getMessage()));
    }
}
