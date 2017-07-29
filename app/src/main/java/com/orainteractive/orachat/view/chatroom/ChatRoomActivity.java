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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.orainteractive.orachat.R;
import com.orainteractive.orachat.base.BaseActivity;
import com.orainteractive.orachat.dagger.components.DaggerChatComponent;
import com.orainteractive.orachat.dagger.module.ChatModule;
import com.orainteractive.orachat.model.ChatMessage;
import com.orainteractive.orachat.model.ChatRoomSend;
import com.orainteractive.orachat.presenter.ChatRoomPresenter;
import com.orainteractive.orachat.view.ChatRoomRecyclerViewAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by kamilabrito on 7/28/17.
 */

public class ChatRoomActivity extends BaseActivity implements ChatRoomView, View.OnClickListener {

    @Inject
    ChatRoomPresenter mPresenter;

    @BindView(R.id.rv_chat_room_messages)
    RecyclerView mRecyclerChatRoom;
    @BindView(R.id.et_chat_room_message)
    EditText etChatRoomMessage;
    @BindView(R.id.ib_chat_room_send)
    ImageButton ibChatRoomSend;

    private int mChatId;

    ChatRoomRecyclerViewAdapter mChatRoomRecylerAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        InputMethodManager imm = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etChatRoomMessage, 0);

        mChatId = getIntent().getIntExtra("CHAT_ID", -1);

        if (mChatId != -1) {
            mPresenter.loadChatRoomMessages(mChatId);
        }

        mChatRoomRecylerAdapter = new ChatRoomRecyclerViewAdapter();
        ibChatRoomSend.setOnClickListener(this);

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

    @Override
    public void loadChatMessages(ChatMessage chatMessages) {
        mChatRoomRecylerAdapter.addChatRoomLocalMessage(chatMessages);
        mChatRoomRecylerAdapter.setmLocalUser(mPresenter.getLoggedUser());
        mRecyclerChatRoom.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerChatRoom.setAdapter(mChatRoomRecylerAdapter);
        mRecyclerChatRoom.smoothScrollToPosition(mChatRoomRecylerAdapter.getItemCount()-1);
        mChatRoomRecylerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ib_chat_room_send:
                mPresenter.sendChatMessage(new ChatRoomSend(etChatRoomMessage.getText().toString()), mChatId);
                etChatRoomMessage.setText("");
                break;
        }
    }
}
