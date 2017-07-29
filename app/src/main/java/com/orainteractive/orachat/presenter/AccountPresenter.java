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

import com.orainteractive.orachat.R;
import com.orainteractive.orachat.base.BasePresenter;
import com.orainteractive.orachat.model.SharedPrefences;
import com.orainteractive.orachat.model.User;
import com.orainteractive.orachat.model.UserResponse;
import com.orainteractive.orachat.model.mapper.CommonMapper;
import com.orainteractive.orachat.services.RetrofitService;
import com.orainteractive.orachat.view.fragment.account.AccountView;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by kamilabrito on 7/29/17.
 */

public class AccountPresenter extends BasePresenter<AccountView> implements Observer<UserResponse> {

    @Inject
    SharedPrefences mPreferences;
    @Inject
    RetrofitService mRetrofit;
    @Inject
    Context mContext;
    @Inject
    CommonMapper mMapper;

    @Inject
    public AccountPresenter() {
    }

    @Override
    public void update(Observable observable, Object o) {

    }

    public void updateAccountInformation(String name, String email, String password, String passwordConfirmation) {

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) {
            getView().showErrorToast();
        } else {
            User updateUser = new User();
            updateUser.setName(name);
            updateUser.setEmail(email);
            updateUser.setPassword(password);
            updateUser.setPasswordConfirmation(passwordConfirmation);

            Observable<UserResponse> userResponseObserver = mRetrofit.updateUserInformation(getToken(), getContentType(), updateUser);
            subscribe(userResponseObserver, this);

        }
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull UserResponse userResponse) {
        User user = mMapper.mapUser(userResponse, getToken(), getContentType());
        mPreferences.saveUserOnStorage(mContext, user);
        getView().confirmUpdate();
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    public String getToken() {
        return mPreferences.readUserFromStorage(mContext).getAuthorization();
    }

    public String getContentType() {
        return mPreferences.readUserFromStorage(mContext).getContentType();
    }
}
