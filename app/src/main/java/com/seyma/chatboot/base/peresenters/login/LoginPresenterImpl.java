package com.seyma.chatboot.base.peresenters.login;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import com.seyma.chatboot.R;
import com.seyma.chatboot.common.utils.UserAuth;
import com.seyma.chatboot.models.User;
import com.seyma.chatboot.views.login.LoginView;

/**
 * @author AOS-SEYMA
 * @since 11-Aug-22
 */
public class LoginPresenterImpl implements LoginPresenter {

    public static final String TAG = "LoginPresenterImpl";

    private Context context;
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(Context context, LoginView loginView) {
        this.context = context;
        this.loginView = loginView;
        loginInteractor = new LoginInteractorImpl(context);
    }

    @Override
    public void onViewDestroy() {
        context = null;
        loginInteractor.onViewDestroy();
    }

    @Override
    public void validateEmailAndPassword(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            loginView.showEmailInputError(context.getString(R.string.enter_email));
            return;
        }
        final String trimEmail = email.trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(trimEmail).matches()){
            loginView.showPasswordInpuitError(context.getString(R.string.enter_password));
        }

        loginView.showProgress();
        loginInteractor.login(trimEmail, password, new OnLoginCompleteListener() {
            @Override
            public void onLoginSuccess(User user) {
                UserAuth.saveUser(context, user);
                loginView.hideProgress();
                loginView.navigateToHomeScreen();
            }

            @Override
            public void onWrongEmailOrPassword() {
                loginView.hideProgress();
                Toast.makeText(context, R.string.wrong_email_or_password , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRequestError(String message) {
                loginView.hideProgress();
                Toast.makeText(context, R.string.unexpected_error_occurred, Toast.LENGTH_LONG).show();
            }
        });

    }

}
