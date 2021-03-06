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

package com.orainteractive.orachat.dagger.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.orainteractive.orachat.dagger.scope.PerActivity;
import com.orainteractive.orachat.services.RetrofitService;
import com.orainteractive.orachat.view.chatroom.ChatRoomView;
import com.orainteractive.orachat.view.fragment.account.AccountView;
import com.orainteractive.orachat.view.fragment.chats.ChatsView;
import com.orainteractive.orachat.view.home.HomeView;
import com.orainteractive.orachat.view.login.LoginView;
import com.orainteractive.orachat.view.splash.SplashView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by kamilabrito on 7/25/17.
 */
@Module
public class ChatModule {

    private Activity mView;

    private Fragment mViewFragment;

    public ChatModule(Activity view) {
        mView = view;
    }

    public ChatModule(Fragment mViewFragment) {
        this.mViewFragment = mViewFragment;
    }

    @PerActivity
    @Provides
    RetrofitService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(RetrofitService.class);
    }

    @PerActivity
    @Provides
    SplashView provideSplashView() {
        return (SplashView) mView;
    }

    @PerActivity
    @Provides
    LoginView provideLoginView() {
        return (LoginView) mView;
    }

    @PerActivity
    @Provides
    HomeView provideHomeView() {
        return (HomeView) mView;
    }

    @PerActivity
    @Provides
    ChatsView provideChatView() {
        return (ChatsView) mViewFragment;
    }

    @PerActivity
    @Provides
    AccountView provideAccountView() {
        return (AccountView) mViewFragment;
    }

    @PerActivity
    @Provides
    ChatRoomView provideChatRoomView() {
        return (ChatRoomView) mView;
    }

}
