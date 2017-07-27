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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.orainteractive.orachat.R;
import com.orainteractive.orachat.base.BasePresenter;
import com.orainteractive.orachat.view.home.HomeView;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by kamilabrito on 7/27/17.
 */

public class HomePresenter extends BasePresenter<HomeView> {

    @Inject
    Context context;

    @Inject
    public HomePresenter() {
    }



    @Override
    public void update(Observable observable, Object o) {

    }
}
