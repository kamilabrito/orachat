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

package com.orainteractive.orachat.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.orainteractive.orachat.R;
import com.orainteractive.orachat.base.BaseFragment;
import com.orainteractive.orachat.dagger.components.DaggerChatComponent;
import com.orainteractive.orachat.dagger.module.ChatModule;
import com.orainteractive.orachat.model.Chats;
import com.orainteractive.orachat.presenter.HomePresenter;
import com.orainteractive.orachat.view.ChatsRecyclerViewAdapter;
import com.orainteractive.orachat.view.OnItemClickListener;
import com.orainteractive.orachat.view.home.HomeView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by kamilabrito on 7/27/17.
 */

public class ChatsFragment extends BaseFragment implements HomeView, OnItemClickListener {

    @Inject
    HomePresenter mPresenter;

    @BindView(R.id.rv_chats_list)
    RecyclerView rvChatsList;

    private ChatsRecyclerViewAdapter chatsRecyclerViewAdapter;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chatsRecyclerViewAdapter = new ChatsRecyclerViewAdapter();
        chatsRecyclerViewAdapter.setOnItemClickListener(this);

        mPresenter.requestChatsList();


    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_chat;
    }

    @Override
    public void onItemClick(Chats item) {

    }

    @Override
    public void loadChatsOnView(List<Chats> chats) {

    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerChatComponent.builder()
                .applicationComponent(getApplicationComponent())
                .chatModule(new ChatModule(getActivity()))
                .build().inject(this);
    }
}
