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

package com.orainteractive.orachat.dagger.components;

import com.orainteractive.orachat.dagger.module.ChatModule;
import com.orainteractive.orachat.dagger.scope.PerActivity;
import com.orainteractive.orachat.view.login.LoginActivity;
import com.orainteractive.orachat.view.splash.SplashActivity;

import dagger.Component;

/**
 * Created by kamilabrito on 7/25/17.
 */
@PerActivity
@Component(modules = ChatModule.class, dependencies = ApplicationComponent.class)
public interface ChatComponent {

    void inject(SplashActivity activity);
    void inject(LoginActivity activity);

}
