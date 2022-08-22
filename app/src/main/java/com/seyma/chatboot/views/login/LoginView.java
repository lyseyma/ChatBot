package com.seyma.chatboot.views.login;

/**
 * @author AOS-SEYMA
 * @since 11-Aug-22
 */
public interface LoginView {
    void showProgress();
    void hideProgress();
    void showEmailInputError(String message);
    void showPasswordInpuitError(String message);
    void navigateToHomeScreen();
}
