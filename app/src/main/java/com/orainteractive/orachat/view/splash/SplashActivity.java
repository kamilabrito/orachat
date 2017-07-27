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

package com.orainteractive.orachat.view.splash;

import android.content.Intent;
import android.os.Bundle;

import com.orainteractive.orachat.R;
import com.orainteractive.orachat.base.BaseActivity;
import com.orainteractive.orachat.dagger.components.DaggerChatComponent;
import com.orainteractive.orachat.dagger.module.ChatModule;
import com.orainteractive.orachat.presenter.SplashPresenter;
import com.orainteractive.orachat.view.home.HomeActivity;
import com.orainteractive.orachat.view.login.LoginActivity;

import javax.inject.Inject;

/**
 * Splash screen activity
 *
 * Created by kamilabrito on 7/25/17.
 */

public class SplashActivity extends BaseActivity implements SplashView {

    @Inject
    SplashPresenter mPresenter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        mPresenter.startSplashTimer();

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }


    @Override
    public void startMainActivity() {
        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
    }

    @Override
    public void starLoginActivity() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerChatComponent.builder()
                .applicationComponent(getApplicationComponent())
                .chatModule(new ChatModule(this))
                .build().inject(this);
    }

}
