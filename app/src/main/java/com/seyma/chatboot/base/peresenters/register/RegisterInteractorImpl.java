package com.seyma.chatboot.base.peresenters.register;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.FirebaseFirestore;
import com.seyma.chatboot.common.Constants;
import com.seyma.chatboot.models.User;

/**
 * @author AOS-SEYMA
 * @since 12-Aug-22
 */
public class RegisterInteractorImpl implements RegisterInteractor {
    @Override
    public void onViewDestroy() {

    }

    @Override
    public void register(String email, String password, String firstName, String lastName, OnRegisterCompleteListener listener) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    saveUserData(authResult.getUser().getUid(), email , firstName, lastName, listener);
                })
                .addOnFailureListener(e -> {
                    if (e instanceof FirebaseAuthUserCollisionException){
                        listener.onEmailExist();
                    }else if (e instanceof FirebaseAuthWeakPasswordException){
                        listener.onPasswordWeek();
                    }else {
                        listener.onRequestError(e.getMessage());
                    }
                });
    }

    private void saveUserData(String id , String email , String firstName , String lastName , OnRegisterCompleteListener listener){
        FirebaseFirestore.getInstance().collection(Constants.USERS_COLLECTION)
                .document(id)
                .set(new User(id , email , firstName , lastName))
                .addOnSuccessListener(documentReference -> listener.onRegisterSuccess(email))
                .addOnFailureListener(e -> listener.onRequestError(e.getMessage()));
    }
}
