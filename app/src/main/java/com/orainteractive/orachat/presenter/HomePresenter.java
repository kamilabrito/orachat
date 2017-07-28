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
import android.util.Log;

import com.orainteractive.orachat.base.BasePresenter;
import com.orainteractive.orachat.model.Chats;
import com.orainteractive.orachat.model.ChatsResponse;
import com.orainteractive.orachat.model.SharedPrefences;
import com.orainteractive.orachat.model.mapper.CommonMapper;
import com.orainteractive.orachat.services.RetrofitService;
import com.orainteractive.orachat.view.fragment.ChatsView;
import com.orainteractive.orachat.view.home.HomeView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Contains all logic related to
 * home screen
 * Created by kamilabrito on 7/27/17.
 */

public class HomePresenter extends BasePresenter<HomeView> implements Observer<ChatsResponse> {

    @Inject
    Context mContext;
    @Inject
    RetrofitService mRetrofit;
    @Inject
    CommonMapper mMapper;
    @Inject
    SharedPrefences mPreferences;

    @Inject
    public HomePresenter() {
    }



    @Override
    public void update(Observable observable, Object o) {

    }

    public void requestChatsList() {
        Log.e("home", "requestChatsList");
        Observable<ChatsResponse> chatsResponseObservable = mRetrofit.getChatsList(getToken(), getContentType(), 1, 50);
        subscribe(chatsResponseObservable, this);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull ChatsResponse chatsResponse) {
        List<Chats> chats = mMapper.mapChats(chatsResponse);
        getView().loadChatsOnView(chats);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        Log.e("home", " ERROR requestChatsList" + e.getMessage());
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
