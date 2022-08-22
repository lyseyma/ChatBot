package com.seyma.chatboot.views.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.seyma.chatboot.R;
import com.seyma.chatboot.base.activity.BaseActivity;
import com.seyma.chatboot.base.peresenters.login.LoginPresenter;
import com.seyma.chatboot.base.peresenters.login.LoginPresenterImpl;
import com.seyma.chatboot.common.custom_view.LoadingDialog;
import com.seyma.chatboot.databinding.ActivityLoginBinding;
import com.seyma.chatboot.views.main.MainActivity;
import com.seyma.chatboot.views.register.RegisterActivity;

import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView , View.OnClickListener {

    private static final int REQUEST_CODE_CREATE_NEW_ACCOUNT = 0;

    private LoadingDialog loadingDialog;

    private ActivityLoginBinding binding;


    @Override
    protected int getLayoutResources() {
        return R.layout.activity_login;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState , ViewDataBinding dataBinding) {

        binding = (ActivityLoginBinding) dataBinding;

        ButterKnife.bind(this);

        loadingDialog = new LoadingDialog(this);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        binding.btnSignIn.setOnClickListener(this);
        binding.txtCreateNewAccount.setOnClickListener(this);

    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenterImpl(this, this);
    }

    @Override
    public void showProgress() {
        loadingDialog.show();

    }

    @Override
    public void hideProgress() {
        loadingDialog.dismiss();

    }

    @Override
    public void showEmailInputError(String message) {
        binding.edtEmail.setError(message);

    }

    @Override
    public void showPasswordInpuitError(String message) {
        binding.edtEmail.setError(message);

    }

    @Override
    public void navigateToHomeScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_in: {
                getPresenter().validateEmailAndPassword(binding.edtEmail.getText().toString(), binding.edtEmail.getText().toString());
            }
            break;

            case R.id.txt_create_new_account: {
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CREATE_NEW_ACCOUNT);
            }
            break;

            default: {
                break;
            }
        }
    }
}