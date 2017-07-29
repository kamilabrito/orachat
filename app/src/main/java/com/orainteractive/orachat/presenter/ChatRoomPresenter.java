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

import com.orainteractive.orachat.base.BasePresenter;
import com.orainteractive.orachat.model.ChatCreate;
import com.orainteractive.orachat.model.ChatCreateResponse;
import com.orainteractive.orachat.model.ChatMessage;
import com.orainteractive.orachat.model.ChatRoomSend;
import com.orainteractive.orachat.model.ChatRoomSendResponse;
import com.orainteractive.orachat.model.Chats;
import com.orainteractive.orachat.model.SharedPrefences;
import com.orainteractive.orachat.model.User;
import com.orainteractive.orachat.model.ChatRoomResponse;
import com.orainteractive.orachat.model.mapper.CommonMapper;
import com.orainteractive.orachat.services.RetrofitService;
import com.orainteractive.orachat.view.chatroom.ChatRoomView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by kamilabrito on 7/28/17.
 */

public class ChatRoomPresenter extends BasePresenter<ChatRoomView> {

    @Inject
    RetrofitService mRetrofit;
    @Inject
    CommonMapper mMapper;
    @Inject
    SharedPrefences mPreferences;
    @Inject
    Context mContext;

    @Inject
    public ChatRoomPresenter() {
    }

    @Override
    public void update(Observable observable, Object o) {

    }

    public void loadChatRoomMessages(int chatId) {
        Observable<ChatRoomResponse> chatRoomResponseObservable =
                mRetrofit.getChatRoomMessages(getToken(), getContentType(), chatId,  1, 50);
        subscribe(chatRoomResponseObservable, new Observer<ChatRoomResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ChatRoomResponse chatRoomResponse) {
                List<ChatMessage> chatMessages = mMapper.mapChatRoom(chatRoomResponse);
                getView().loadChatMessages(chatMessages);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public String getToken() {
        return mPreferences.readUserFromStorage(mContext).getAuthorization();
    }

    public String getContentType() {
        return mPreferences.readUserFromStorage(mContext).getContentType();
    }

    public User getLoggedUser() {
        return mPreferences.readUserFromStorage(mContext);
    }

    public void sendChatMessage(ChatRoomSend message, int chatId) {
        if (!message.getMessage().isEmpty()) {
            Observable<ChatRoomSendResponse> chatRoomSendResponseObservable =
                    mRetrofit.sendChatRoomMessages(getToken(), getContentType(), chatId, message);
            subscribe(chatRoomSendResponseObservable, new Observer<ChatRoomSendResponse>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onNext(@NonNull ChatRoomSendResponse chatRoomSendResponse) {
                    ChatMessage chatMessages = mMapper.mapChatRoomSend(chatRoomSendResponse);
                    getView().loadChatMessages(chatMessages);
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
        }

    }

    public void editChat(int chatId, ChatCreate chatCreate) {

        Observable<ChatCreateResponse> chatsEditResponseObservable = mRetrofit.updateChat(getToken(), getContentType(), chatId, chatCreate);
        subscribe(chatsEditResponseObservable, new Observer<ChatCreateResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ChatCreateResponse chatCreateResponse) {
                Chats chatMessage = mMapper.mapCreateChat(chatCreateResponse);
                getView().updateChatName(chatMessage.getName());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                getView().showErrorToast();

            }

            @Override
            public void onComplete() {

            }
        });



    }

    public void openEditChatView() {
        getView().editChatView();
    }

    public void hideEditMode() {
        getView().hideEditChatView();
    }
}
