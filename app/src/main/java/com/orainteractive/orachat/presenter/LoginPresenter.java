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

package com.orainteractive.orachat.presenter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.orainteractive.orachat.R;
import com.orainteractive.orachat.base.BasePresenter;
import com.orainteractive.orachat.model.Login;
import com.orainteractive.orachat.model.SharedPrefences;
import com.orainteractive.orachat.model.User;
import com.orainteractive.orachat.model.UserResponse;
import com.orainteractive.orachat.model.mapper.UserMapper;
import com.orainteractive.orachat.services.RetrofitService;
import com.orainteractive.orachat.view.login.LoginView;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kamilabrito on 7/25/17.
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    @Inject
    RetrofitService mRetrofit;
    @Inject
    UserMapper mUserMapper;
    @Inject
    SharedPrefences mPreferences;
    @Inject
    Context context;

    @Inject
    public LoginPresenter(){

    }


    public void openRegisterView() {
        getView().hideLoginView();
        getView().showRegisterView();
    }

    public void loginWithExistingUser(String email, String password) {

        Login login = new Login(email, password);

        if (login.getEmail().isEmpty() || login.getPassword().isEmpty()) {
            getView().showEmptyFieldError();
        } else {
          Call<UserResponse> responseCall = mRetrofit.loginWithUser(login);

            responseCall.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                    Headers headers = response.headers();
                    headers.get(context.getString(R.string.authorization)).toString();

                    User user = mUserMapper.mapUser(response.body(), headers.get(context.getString(R.string.authorization)).toString());
                    getView().openHomeScreen(user);
                    mPreferences.saveUserOnStorage((Activity)getView(), user);
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {

                }
            });
        }

    }

    public void registerNewUser(String name, String email, String password, String passwordConfimation) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setPasswordConfirmation(passwordConfimation);

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConfimation.isEmpty()) {
            getView().showEmptyFieldError();
        } else {

            Call<UserResponse> newUserCall = mRetrofit.createNewUser(newUser);
            newUserCall.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    Headers headers = response.headers();
                    headers.get(context.getString(R.string.authorization)).toString();

                    User user = mUserMapper.mapUser(response.body(), headers.get(context.getString(R.string.authorization)).toString());
                    getView().openHomeScreen(user);
                    mPreferences.saveUserOnStorage((Activity)getView(), user);
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {

                }
            });
        }

    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
