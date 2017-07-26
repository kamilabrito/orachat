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

import android.os.Handler;

import com.orainteractive.orachat.base.BasePresenter;
import com.orainteractive.orachat.view.splash.SplashView;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 *Splash screen presenter
 * Created by kamilabrito on 7/25/17.
 */

public class SplashPresenter extends BasePresenter<SplashView> {


    @Inject
    public SplashPresenter() {

    }

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
                }, 500);
    }

    public boolean isUserLoggedIn(){
        return false;
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
