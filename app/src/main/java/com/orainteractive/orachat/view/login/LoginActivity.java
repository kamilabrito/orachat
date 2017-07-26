/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *            http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.orainteractive.orachat.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.orainteractive.orachat.R;
import com.orainteractive.orachat.base.BaseActivity;
import com.orainteractive.orachat.dagger.components.DaggerChatComponent;
import com.orainteractive.orachat.dagger.module.ChatModule;
import com.orainteractive.orachat.model.User;
import com.orainteractive.orachat.presenter.LoginPresenter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by kamilabrito on 7/25/17.
 */

public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener {

    @BindView(R.id.et_login_email)
    EditText editTextLoginEmail;
    @BindView(R.id.et_login_password)
    EditText editTextLoginPassword;
    @BindView(R.id.btn_login_enter)
    Button buttonLoginEnter;
    @BindView(R.id.btn_login_register)
    Button buttonLoginRegister;
    @BindView(R.id.ll_login)
    LinearLayout linearLayoutLogin;
    @BindView(R.id.ll_register)
    LinearLayout linearLayoutRegister;
    @BindView(R.id.et_register_name)
    EditText editTextRegisterName;
    @BindView(R.id.et_register_email)
    EditText editTextRegisterEmail;
    @BindView(R.id.et_register_password)
    EditText editTextRegisterPassword;
    @BindView(R.id.et_register_confirm_password)
    EditText editTextRegisterConfirmPassword;
    @BindView(R.id.btn_register_register)
    Button buttonRegisterRegister;


    @Inject
    LoginPresenter mPresenter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        buttonLoginRegister.setOnClickListener(this);
        buttonRegisterRegister.setOnClickListener(this);
        buttonLoginEnter.setOnClickListener(this);


    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerChatComponent.builder()
                .applicationComponent(getApplicationComponent())
                .chatModule(new ChatModule(this))
                .build().inject(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_register:
                mPresenter.openRegisterView();
                break;
            case R.id.btn_login_enter:
                Log.e("onClick","R.id.btn_login_enter");
                mPresenter.loginWithExistingUser(
                        editTextLoginEmail.getText().toString(),
                        editTextLoginPassword.getText().toString());
                break;
            case R.id.btn_register_register:
                mPresenter.registerNewUser();
                break;
        }
    }

    @Override
    public void hideLoginView() {
        linearLayoutLogin.setVisibility(View.GONE);
    }

    @Override
    public void showRegisterView() {
        linearLayoutRegister.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyFieldError() {
        Toast.makeText(this, getApplicationContext().getResources().
                getString(R.string.empty_field), Toast.LENGTH_LONG).show();
    }

    @Override
    public void openMainScreen(User user) {
        Log.e("login","openMainScreen: " + user.getEmail());
    }

}
