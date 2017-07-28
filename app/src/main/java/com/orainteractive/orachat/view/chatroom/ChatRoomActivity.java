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

package com.orainteractive.orachat.view.chatroom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.orainteractive.orachat.R;
import com.orainteractive.orachat.base.BaseActivity;
import com.orainteractive.orachat.dagger.components.DaggerChatComponent;
import com.orainteractive.orachat.dagger.module.ChatModule;
import com.orainteractive.orachat.model.ChatMessage;
import com.orainteractive.orachat.model.User;
import com.orainteractive.orachat.presenter.ChatRoomPresenter;
import com.orainteractive.orachat.view.ChatRoomRecyclerViewAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by kamilabrito on 7/28/17.
 */

public class ChatRoomActivity extends BaseActivity implements ChatRoomView {

    @Inject
    ChatRoomPresenter mPresenter;

    @BindView(R.id.rv_chat_room_messages)
    RecyclerView mRecyclerChatRoom;

    ChatRoomRecyclerViewAdapter mChatRoomRecylerAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        int chatId = getIntent().getIntExtra("CHAT_ID", -1);

        if (chatId != -1) {
            mPresenter.loadChatRoomMessages(chatId);
        }

        mChatRoomRecylerAdapter = new ChatRoomRecyclerViewAdapter();

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_chatroom;
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerChatComponent.builder()
                .applicationComponent(getApplicationComponent())
                .chatModule(new ChatModule(this))
                .build().inject(this);
    }

    @Override
    public void loadChatMessages(List<ChatMessage> chatMessages) {
        mChatRoomRecylerAdapter.addChatRoomMessages(chatMessages);
        mChatRoomRecylerAdapter.setmLocalUser(mPresenter.getLoggedUser());
        mRecyclerChatRoom.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerChatRoom.setAdapter(mChatRoomRecylerAdapter);
        mChatRoomRecylerAdapter.notifyDataSetChanged();

    }
}
