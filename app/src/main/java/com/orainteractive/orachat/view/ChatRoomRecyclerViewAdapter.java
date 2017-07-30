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

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orainteractive.orachat.R;
import com.orainteractive.orachat.model.ChatMessage;
import com.orainteractive.orachat.model.Chats;
import com.orainteractive.orachat.model.User;
import com.orainteractive.orachat.util.Utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

/**
 * Created by kamilabrito on 7/27/17.
 */

public class ChatRoomRecyclerViewAdapter extends RecyclerView.Adapter<ChatRoomRecyclerViewAdapter.ViewHolder> {

    private List<ChatMessage> mChatRoomMessages = new ArrayList<>();
    private User mLocalUser;
    private Context mContext;

    public ChatRoomRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_room_messages, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChatRoomRecyclerViewAdapter.ViewHolder holder, int position) {
        final ChatMessage currentChat = mChatRoomMessages.get(position);

        if (currentChat != null) {
            if (currentChat.getUser().getId() == getmLocalUser().getId()) {
                holder.llOtherUser.setVisibility(View.GONE);
                holder.llLocalUser.setVisibility(View.VISIBLE);
                holder.tvLocalMessage.setText(currentChat.getMessage());
                try {
                    holder.tvLocalUserAndTime.setText(currentChat.getUser().getName()
                                + " - " + Utils.getDateAgo(currentChat.getCreated_at(), mContext));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } else {
                holder.llLocalUser.setVisibility(View.GONE);
                holder.llOtherUser.setVisibility(View.VISIBLE);
                holder.tvOtherMessage.setText(currentChat.getMessage());
                try {
                    holder.tvOtherUserAndTime.setText(currentChat.getUser().getName()
                                + " - " + Utils.getDateAgo(currentChat.getCreated_at(), mContext));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    @Override
    public int getItemCount() {
        return (null != mChatRoomMessages ? mChatRoomMessages.size() : 0);
    }

    public void addChatRoomMessages(List<ChatMessage> chats) {
        mChatRoomMessages.addAll(chats);
        notifyDataSetChanged();
    }

    public void addChatRoomLocalMessage(ChatMessage chatMessages) {
        mChatRoomMessages.add(chatMessages);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.ll_chat_room_other_user)
        RelativeLayout llOtherUser;
        @BindView(R.id.ll_chat_room_local_user)
        RelativeLayout llLocalUser;
        @BindView(R.id.tv_chat_room_message)
        TextView tvOtherMessage;
        @BindView(R.id.tv_chat_room_user_time)
        TextView tvOtherUserAndTime;
        @BindView(R.id.tv_chat_room_local_message)
        TextView tvLocalMessage;
        @BindView(R.id.tv_chat_room_local_user_time)
        TextView tvLocalUserAndTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public User getmLocalUser() {
        return mLocalUser;
    }

    public void setmLocalUser(User mLocalUser) {
        this.mLocalUser = mLocalUser;
    }
}
