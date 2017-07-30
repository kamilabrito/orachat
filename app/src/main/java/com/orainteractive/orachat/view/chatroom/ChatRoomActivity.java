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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.orainteractive.orachat.R;
import com.orainteractive.orachat.base.BaseActivity;
import com.orainteractive.orachat.dagger.components.DaggerChatComponent;
import com.orainteractive.orachat.dagger.module.ChatModule;
import com.orainteractive.orachat.model.ChatCreate;
import com.orainteractive.orachat.model.ChatMessage;
import com.orainteractive.orachat.model.ChatRoomSend;
import com.orainteractive.orachat.model.Chats;
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
    @BindView(R.id.ll_edit_chat)
    LinearLayout llEditChat;
    @BindView(R.id.et_edit_chat_name)
    EditText etEditChatName;
    @BindView(R.id.btn_edit_chat)
    Button btnEditChat;
    @BindView(R.id.bottom_bar)
    RelativeLayout rlBottomBar;
    @BindView(R.id.pb_chatroom)
    ProgressBar pbChatRoom;

    private Chats mChat;

    ChatRoomRecyclerViewAdapter mChatRoomRecylerAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        InputMethodManager imm = (InputMethodManager)getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etChatRoomMessage, 0);

        try {
            mChat = (Chats) getIntent().getSerializableExtra("CHAT_INFO");
            if (mChat != null) {
                pbChatRoom.setVisibility(View.VISIBLE);
                mPresenter.loadChatRoomMessages(mChat.getId());
                getSupportActionBar().setTitle(mChat.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        mChatRoomRecylerAdapter = new ChatRoomRecyclerViewAdapter();
        ibChatRoomSend.setOnClickListener(this);
        mChatRoomRecylerAdapter.setmLocalUser(mPresenter.getLoggedUser());
        mRecyclerChatRoom.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerChatRoom.setAdapter(mChatRoomRecylerAdapter);
        btnEditChat.setOnClickListener(this);

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
        pbChatRoom.setVisibility(View.GONE);
        mChatRoomRecylerAdapter.addChatRoomMessages(chatMessages);
    }

    @Override
    public void loadChatMessages(ChatMessage chatMessages) {
        pbChatRoom.setVisibility(View.GONE);
        mChatRoomRecylerAdapter.addChatRoomLocalMessage(chatMessages);
        mRecyclerChatRoom.smoothScrollToPosition(mChatRoomRecylerAdapter.getItemCount()-1);
    }

    @Override
    public void editChatView() {
        llEditChat.setVisibility(View.VISIBLE);
        mRecyclerChatRoom.setVisibility(View.GONE);
        rlBottomBar.setVisibility(View.GONE);
    }

    @Override
    public void updateChatName(String chatName) {
        pbChatRoom.setVisibility(View.GONE);
        mChat.setName(chatName);
        getSupportActionBar().setTitle(mChat.getName());
    }

    @Override
    public void showErrorToast() {
        pbChatRoom.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), R.string.request_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideEditChatView() {
        llEditChat.setVisibility(View.GONE);
        mRecyclerChatRoom.setVisibility(View.VISIBLE);
        rlBottomBar.setVisibility(View.VISIBLE);
        etEditChatName.setText("");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ib_chat_room_send:
                mPresenter.sendChatMessage(new ChatRoomSend(etChatRoomMessage.getText().toString()), mChat.getId());
                etChatRoomMessage.setText("");
                break;
            case R.id.btn_edit_chat:
                pbChatRoom.setVisibility(View.VISIBLE);
                mPresenter.editChat(mChat.getId(), etEditChatName.getText().toString());
                mPresenter.hideEditMode();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_room_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_chat:
                mPresenter.openEditChatView();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Shows toast with empty fields message
     */
    @Override
    public void showEmptyFieldError() {
        pbChatRoom.setVisibility(View.GONE);
        Toast.makeText(this, getApplicationContext().getResources().
                getString(R.string.empty_field), Toast.LENGTH_LONG).show();
    }
}
