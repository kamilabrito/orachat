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
import com.orainteractive.orachat.model.ChatCreate;
import com.orainteractive.orachat.model.ChatCreateResponse;
import com.orainteractive.orachat.model.Chats;
import com.orainteractive.orachat.model.SharedPrefences;
import com.orainteractive.orachat.model.mapper.CommonMapper;
import com.orainteractive.orachat.services.RetrofitService;
import com.orainteractive.orachat.util.Utils;
import com.orainteractive.orachat.view.home.HomeView;

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

public class HomePresenter extends BasePresenter<HomeView> implements Observer<ChatCreateResponse> {

    @Inject
    RetrofitService mRetrofit;
    @Inject
    CommonMapper mMapper;
    @Inject
    SharedPrefences mPreferences;
    @Inject
    Context mContext;

    @Inject
    public HomePresenter() {
    }


    @Override
    public void update(Observable observable, Object o) {

    }

    public void openNewChatScreen() {
        if (getView().createNewChatIsVisible()) {
            getView().hideCreateNewChatView();
        } else {
            getView().showNewChatView();
        }

    }

    /**
     * Creates new chat based on information given by the user
     *
     * @param name
     * @param message
     */
    public void createNewChat(String name, String message) {
        if (name.isEmpty() || message.isEmpty()) {
            getView().showError(R.string.empty_field);
        } else {
            if (Utils.isNetAvailable(mContext)) {
                ChatCreate chatCreate = new ChatCreate(name, message);
                Observable<ChatCreateResponse> chatsCreateResponseObservable = mRetrofit.createNewChat(getToken(), getContentType(), chatCreate);
                subscribe(chatsCreateResponseObservable, this);
            } else {
                getView().showError(R.string.network_error);
            }
        }
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull ChatCreateResponse chatCreateResponse) {
        Chats chatMessages = mMapper.mapCreateChat(chatCreateResponse);
        getView().updateChatList(chatMessages);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        getView().showError(R.string.request_error);
        e.printStackTrace();
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
