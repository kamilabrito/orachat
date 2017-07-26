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

import android.util.Log;

import com.orainteractive.orachat.base.BasePresenter;
import com.orainteractive.orachat.model.Login;
import com.orainteractive.orachat.model.User;
import com.orainteractive.orachat.model.UserResponse;
import com.orainteractive.orachat.model.mapper.UserMapper;
import com.orainteractive.orachat.services.RetrofitService;
import com.orainteractive.orachat.view.login.LoginView;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by kamilabrito on 7/25/17.
 */

public class LoginPresenter extends BasePresenter<LoginView> implements Observer<UserResponse> {

    @Inject
    RetrofitService mRetrofit;
    @Inject
    UserMapper mUserMapper;

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
            Log.e("login","email.isEmpty() || password.isEmpty()");
        } else {
            Log.e("login","not empty");
            Observable<UserResponse> userResponseObservable = mRetrofit.loginWithExistingUser(login);
            subscribe(userResponseObservable, this);
        }

    }

    public void registerNewUser() {
    }

    @Override
    public void update(Observable observable, Object o) {

    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull UserResponse userResponse) {
        User user = mUserMapper.mapUser(userResponse);
        getView().openMainScreen(user);

    }

    @Override
    public void onError(@NonNull Throwable e) {
        Log.e("login", e.getLocalizedMessage());

    }

    @Override
    public void onComplete() {

    }
}
