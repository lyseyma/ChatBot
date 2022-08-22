package com.seyma.chatboot.base.peresenters.login;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.seyma.chatboot.common.Constants;
import com.seyma.chatboot.models.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AOS-SEYMA
 * @since 11-Aug-22
 */
public class LoginInteractorImpl implements LoginInteractor{

    private Context context;

    public LoginInteractorImpl(Context context) {
        this.context = context;
    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void login(String username, String password, OnLoginCompleteListener listener) {

        FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
                .addOnSuccessListener(authResult -> updateUserData(authResult.getUser().getUid(), listener))
                .addOnFailureListener(e -> listener.onRequestError(e.getMessage()));

    }
    private void updateUserData(String userID, OnLoginCompleteListener listener) {
        DocumentReference userRef = FirebaseFirestore.getInstance()
                .collection(Constants.USERS_COLLECTION)
                .document(userID);
        FirebaseFirestore.getInstance().runTransaction(transaction -> {
            User user = transaction.get(userRef).toObject(User.class);
            Map<String, Object> onlineStateData = new HashMap<>(1);
            onlineStateData.put(User.IS_ONLINE, true);
            transaction.set(userRef, onlineStateData, SetOptions.merge());
            return user;
        }).addOnSuccessListener(user -> {
            FirebaseMessaging firebaseMessaging = FirebaseMessaging.getInstance();
            firebaseMessaging.subscribeToTopic(userID);
            listener.onLoginSuccess(user);
        }).addOnFailureListener(e -> listener.onRequestError(e.getMessage()));
    }

}
