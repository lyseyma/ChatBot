package com.seyma.chatboot.base.peresenters.register;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import com.seyma.chatboot.R;
import com.seyma.chatboot.views.register.RegisterView;

/**
 * @author AOS-SEYMA
 * @since 12-Aug-22
 */
public class RegisterPresenterImpl implements RegisterPresenter {
    public static final String TAG = "RegisterPresenterImpl";

    private Context context;
    private RegisterView view;
    private RegisterInteractor interactor;

    public RegisterPresenterImpl(Context context , RegisterView view){
        this.context = context;
        this.view = view;
        this.interactor = new RegisterInteractorImpl();
    }


    @Override
    public void onViewDestroy() {
        context = null;
        interactor.onViewDestroy();
    }

    @Override
    public void validateRegisterForm(String firstName, String lastName, String email, String password, String confirmPassword) {

        if (TextUtils.isEmpty(firstName)){
            view.showFirstNameInputError(context.getString(R.string.enter_first_name));
            return;
        }
        firstName = firstName.trim();
        if (TextUtils.isEmpty(lastName)) {
            view.showLastNameInputError(context.getString(R.string.enter_last_name));
            return;
        }
        lastName = lastName.trim();
        if (TextUtils.isEmpty(email)){
            view.showEmailInputError(context.getString(R.string.enter_email));
            return;
        }
        email = email.trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.showEmailInputError(context.getString(R.string.invalid_email));
            return;
        }

        if (TextUtils.isEmpty(password)){
            view.showPasswordInputError(context.getString(R.string.enter_password));
            return;
        }
         if (TextUtils.isEmpty(confirmPassword)){
             view.showConfirmPasswordInputError(context.getString(R.string.confirm_password));
             return;
         }
         if (!confirmPassword.equalsIgnoreCase(password)){
             view.showConfirmPasswordInputError(context.getString(R.string.confirm_password_mismatch));
             return;
         }
         view.showProgress();

         interactor.register(email, password, firstName, lastName, new OnRegisterCompleteListener() {
             @Override
             public void onRegisterSuccess(String email) {
                 view.hideProgress();
                 view.navigateToLoginScreen(email);
             }

             @Override
             public void onEmailExist() {
                 view.hideProgress();
                 view.showEmailInputError(context.getString(R.string.email_exist));
             }

             @Override
             public void onPasswordWeek() {
                 view.hideProgress();
                 view.showPasswordInputError(context.getString(R.string.password_should_be_at_leas_6_characters));
             }

             @Override
             public void onRequestError(String message) {
                 view.hideProgress();
                 Toast.makeText(context, R.string.unexpected_error_occurred, Toast.LENGTH_LONG).show();

             }
         });


    }
}
