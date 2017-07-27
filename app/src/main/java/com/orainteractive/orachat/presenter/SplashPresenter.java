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
import android.os.Handler;

import com.orainteractive.orachat.base.BasePresenter;
import com.orainteractive.orachat.model.SharedPrefences;
import com.orainteractive.orachat.model.User;
import com.orainteractive.orachat.view.splash.SplashView;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Contains all logic related to
 * splash screen
 * Created by kamilabrito on 7/25/17.
 */

public class SplashPresenter extends BasePresenter<SplashView> {

    @Inject
    Context context;
    @Inject
    SharedPrefences mPreferences;

    @Inject
    public SplashPresenter() {

    }

    /**
     * Shows splash scree for one second before
     * changing into home screen or login screen
     */
    public void startSplashTimer() {
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        if (isUserLoggedIn()) {
                            getView().startMainActivity();
                        } else {
                            getView().starLoginActivity();
                        }

                    }
                }, 1000);
    }

    /**
     * Verifies if user is already logged in the application
     *
     * @return
     */
    public boolean isUserLoggedIn() {
        User user = mPreferences.readUserFromStorage(context);
        if (user.getAuthorization() != null) {
            return true;
        }
        return false;
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
