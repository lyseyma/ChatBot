package com.seyma.chatboot.views.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.seyma.chatboot.R;
import com.seyma.chatboot.base.activity.BaseActivity;
import com.seyma.chatboot.base.peresenters.register.RegisterPresenter;
import com.seyma.chatboot.base.peresenters.register.RegisterPresenterImpl;
import com.seyma.chatboot.common.Constants;
import com.seyma.chatboot.common.custom_view.LoadingDialog;
import com.seyma.chatboot.databinding.ActivityRegisterBinding;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterView , View.OnClickListener {

    private LoadingDialog loadingDialog;

    private ActivityRegisterBinding binding;

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_register;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState , ViewDataBinding dataBinding) {
        binding = (ActivityRegisterBinding) dataBinding;
        ButterKnife.bind(this);
        loadingDialog = new LoadingDialog(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        binding.btnRegister.setOnClickListener(this);
        binding.txtSignIn.setOnClickListener(this);
    }

    @Override
    protected RegisterPresenter initPresenter() {
        return new RegisterPresenterImpl(this, this);
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
    public void showFirstNameInputError(String message) {
        binding.edtFirstName.setError(message);
    }

    @Override
    public void showLastNameInputError(String message) {
        binding.edtLastName.setError(message);
    }

    @Override
    public void showEmailInputError(String message) {
        binding.edtEmail.setError(message);
    }

    @Override
    public void showPasswordInputError(String message) {
        binding.edtPassword.setError(message);
    }

    @Override
    public void showConfirmPasswordInputError(String message) {
        binding.edtConfirmPassword.setError(message);
    }

    @Override
    public void navigateToLoginScreen(String email) {
        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_EMAIL, email);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register: {
                getPresenter().validateRegisterForm(binding.edtFirstName.getText().toString(),
                        binding.edtLastName.getText().toString(),
                        binding.edtEmail.getText().toString(),
                        binding.edtPassword.getText().toString(),
                        binding.edtConfirmPassword.getText().toString());
            }
            break;

            case R.id.txt_sign_in: {
                finish();
            }
            break;

            default: {
                break;
            }
        }
    }
}