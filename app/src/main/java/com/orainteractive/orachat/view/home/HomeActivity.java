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

package com.orainteractive.orachat.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.orainteractive.orachat.R;
import com.orainteractive.orachat.base.BaseActivity;
import com.orainteractive.orachat.dagger.components.DaggerChatComponent;
import com.orainteractive.orachat.dagger.module.ChatModule;
import com.orainteractive.orachat.model.ChatCreate;
import com.orainteractive.orachat.model.Chats;
import com.orainteractive.orachat.presenter.HomePresenter;
import com.orainteractive.orachat.view.fragment.AccountFragment;
import com.orainteractive.orachat.view.fragment.ChatsFragment;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by kamilabrito on 7/27/17.
 */

public class HomeActivity extends BaseActivity implements HomeView, View.OnClickListener {

    @BindView(R.id.bn_home)
    BottomNavigationView mBnHome;
    @BindView(R.id.fab_home_create_chat)
    FloatingActionButton mFabCreateChat;
    @BindView(R.id.ll_create_chat)
    LinearLayout mLLCreateChat;
    @BindView(R.id.et_home_chat_name)
    EditText mEtChatName;
    @BindView(R.id.et_home_first_message)
    EditText mEtFirstMessage;
    @BindView(R.id.btn_home_create_chat)
    Button mBtnCreateChat;

    @Inject
    HomePresenter mPresenter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        mFabCreateChat.setOnClickListener(this);
        mBtnCreateChat.setOnClickListener(this);
        mBnHome.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                handleBottomNavigationItemSelected(item);
                return true;
            }
        });

        changeFragment(new ChatsFragment());

    }

    private void changeFragment(Fragment fragment) {
        FragmentTransaction cf = getSupportFragmentManager().beginTransaction();
        cf.replace(R.id.fragmentContainer, fragment);
        cf.commit();
    }

    private void changeFragment(Fragment fragment, Bundle information) {
        FragmentTransaction cf = getSupportFragmentManager().beginTransaction();
        fragment.setArguments(information);
        cf.replace(R.id.fragmentContainer, fragment);
        cf.commit();
    }


    private void handleBottomNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_chats:
                changeFragment(new ChatsFragment());
                break;
            case R.id.action_account:
                changeFragment(new AccountFragment());
                break;
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_home_create_chat:
                mPresenter.openNewChatScreen();
                break;
            case R.id.btn_home_create_chat:
                mPresenter.createNewChat(new ChatCreate(mEtChatName.getText().toString(),
                        mEtFirstMessage.getText().toString()));
                break;
        }
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerChatComponent.builder()
                .applicationComponent(getApplicationComponent())
                .chatModule(new ChatModule(this))
                .build().inject(this);
    }

    @Override
    public void showNewChatView() {
        mLLCreateChat.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateChatList(Chats chatMessages) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("NEW_CHAT", chatMessages);
        changeFragment(new ChatsFragment(), bundle);
        mLLCreateChat.setVisibility(View.GONE);
    }
}
