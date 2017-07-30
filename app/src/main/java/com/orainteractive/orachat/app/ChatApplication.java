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

package com.orainteractive.orachat.app;

import android.app.Application;

import com.orainteractive.orachat.dagger.components.ApplicationComponent;
import com.orainteractive.orachat.dagger.components.DaggerApplicationComponent;
import com.orainteractive.orachat.dagger.module.ApplicationModule;


/**
 * Custom base Application class with dagger
 * injection tor maintain the global state
 * of the application
 * Created by kamilabrito on 7/25/17.
 */

public class ChatApplication extends Application {

    private ApplicationComponent mApplicationComponent;
    private final static String BASE_URL = "https://private-anon-aca127045c-oracodechallenge.apiary-mock.com/";

    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, BASE_URL))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
