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

package com.orainteractive.orachat.view;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orainteractive.orachat.R;
import com.orainteractive.orachat.model.Chats;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kamilabrito on 7/27/17.
 */

public class ChatsRecyclerViewAdapter extends RecyclerView.Adapter<ChatsRecyclerViewAdapter.ViewHolder> {

    private OnItemClickListener onItemClickListener;
    private List<Chats> mChatsList = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chat, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChatsRecyclerViewAdapter.ViewHolder holder, int position) {
        final Chats currentChat = mChatsList.get(position);

        if (currentChat != null) {

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(currentChat);
                }
            };
            holder.cvChatCard.setOnClickListener(listener);
            holder.tvHeaderDate.setText(currentChat.getLast_chat_message().getCreated_at());
            holder.tvChatNameAndUser.setText(currentChat.getName() + " " + currentChat.getUsers().get(0).getName());
            holder.tvLastMessage.setText(currentChat.getLast_chat_message().getMessage());
            holder.tvLastUserTime.setText(currentChat.getLast_chat_message().getCreated_at());
        }

    }

    @Override
    public int getItemCount() {
        return (null != mChatsList ? mChatsList.size() : 0);
    }

    public void addChats(List<Chats> chats) {
        mChatsList.addAll(chats);
    }

    public void addNewChat(Chats newChat) {
        if (newChat != null) {
            mChatsList.add(newChat);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_chat_header_date)
        TextView tvHeaderDate;
        @BindView(R.id.tv_chat_last_message)
        TextView tvLastMessage;
        @BindView(R.id.tv_chat_last_user_time)
        TextView tvLastUserTime;
        @BindView(R.id.tv_chat_name_and_user)
        TextView tvChatNameAndUser;
        @BindView(R.id.row_card_chat)
        CardView cvChatCard;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
