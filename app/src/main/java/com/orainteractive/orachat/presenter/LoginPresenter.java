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

import android.content.Context;
import android.content.pm.PackageInfo;

import com.orainteractive.orachat.R;
import com.orainteractive.orachat.base.BasePresenter;
import com.orainteractive.orachat.model.Login;
import com.orainteractive.orachat.model.SharedPrefences;
import com.orainteractive.orachat.model.User;
import com.orainteractive.orachat.model.UserResponse;
import com.orainteractive.orachat.model.mapper.CommonMapper;
import com.orainteractive.orachat.services.RetrofitService;
import com.orainteractive.orachat.util.Utils;
import com.orainteractive.orachat.view.login.LoginView;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Contains all logic related to
 * login screen
 * Created by kamilabrito on 7/25/17.
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    @Inject
    RetrofitService mRetrofit;
    @Inject
    CommonMapper mCommonMapper;
    @Inject
    SharedPrefences mPreferences;
    @Inject
    Context mContext;

    @Inject
    public LoginPresenter() {

    }

    /**
     * Open view to register user
     */
    public void openRegisterView() {
        getView().hideLoginView();
        getView().showRegisterView();
    }

    /**
     * Performs loggin with an existing user
     *
     * @param email
     * @param password
     */
    public void loginWithExistingUser(String email, String password) {

        if (email.isEmpty() || password.isEmpty()) {
            getView().showError(R.string.empty_field);
        } else {
            if (Utils.isNetAvailable(mContext)) {
                Login login = new Login(email, password);

                Call<UserResponse> responseCall = mRetrofit.loginWithUser(login);

                responseCall.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                        Headers headers = response.headers();

                        User user = mCommonMapper.mapUser(response.body(), headers.get(mContext.getString(R.string.authorization)).toString(),
                                headers.get(mContext.getResources().getString(R.string.content_type)).toString());
                        getView().openHomeScreen();
                        mPreferences.saveUserOnStorage(mContext, user);
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        getView().showError(R.string.request_error);
                        t.printStackTrace();
                    }
                });
            } else {
                getView().showError(R.string.network_error);
            }
        }

    }

    /**
     * Sends information about new user during registration
     * also performs login of the new user with the return from
     * the server
     *
     * @param name
     * @param email
     * @param password
     * @param passwordConfimation
     */
    public void registerNewUser(String name, String email, String password, String passwordConfimation) {

        if (!password.equals(passwordConfimation)) {
            getView().showError(R.string.password_not_mathing);
        } else if (name.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConfimation.isEmpty()) {
            getView().showError(R.string.empty_field);
        } else {
            if (Utils.isNetAvailable(mContext)) {
                User newUser = new User();
                newUser.setName(name);
                newUser.setEmail(email);
                newUser.setPassword(password);
                newUser.setPasswordConfirmation(passwordConfimation);

                Call<UserResponse> newUserCall = mRetrofit.createNewUser(newUser);
                newUserCall.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        Headers headers = response.headers();

                        User user = mCommonMapper.mapUser(response.body(), headers.get(mContext.getString(R.string.authorization)).toString(),
                                headers.get(mContext.getResources().getString(R.string.content_type)).toString());
                        getView().openHomeScreen();
                        mPreferences.saveUserOnStorage(mContext, user);
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        getView().showError(R.string.request_error);
                        t.printStackTrace();
                    }
                });
            } else {
                getView().showError(R.string.network_error);
            }
        }

    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
