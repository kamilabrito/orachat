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

package com.orainteractive.orachat.model.mapper;

import com.orainteractive.orachat.model.ChatCreateResponse;
import com.orainteractive.orachat.model.ChatMessage;
import com.orainteractive.orachat.model.ChatRoomResponse;
import com.orainteractive.orachat.model.ChatRoomSendResponse;
import com.orainteractive.orachat.model.Chats;
import com.orainteractive.orachat.model.ChatsResponse;
import com.orainteractive.orachat.model.User;
import com.orainteractive.orachat.model.UserResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Parser user information from the server response to
 * create an user object
 * Created by kamilabrito on 7/26/17.
 */

public class CommonMapper {

    @Inject
    public CommonMapper() {
    }

    /**
     * Maps user information retrieved from the server
     *
     * @param response
     * @param authorization
     * @param contentType
     * @return
     */
    public User mapUser(UserResponse response, String authorization, String contentType) {
        User myUser = new User();

        if (response != null) {
            myUser.setId(response.getData().getId());
            myUser.setName(response.getData().getName());
            myUser.setEmail(response.getData().getEmail());
            myUser.setAuthorization(authorization);
            myUser.setContentType(contentType);
        }

        return myUser;
    }

    /**
     * Maps chats response received from the server
     *
     * @param response
     * @return
     */
    public List<Chats> mapChats(ChatsResponse response) {
        List<Chats> chatsList = new ArrayList<>();

        if (response != null) {
            chatsList.addAll(response.getData());
        }
        return chatsList;
    }

    /**
     * Maps the list of chat rooms response from the server
     *
     * @param chatRoomResponse
     * @return
     */
    public List<ChatMessage> mapChatRoom(ChatRoomResponse chatRoomResponse) {
        List<ChatMessage> chatsMessageList = new ArrayList<>();

        if (chatRoomResponse != null) {
            chatsMessageList.addAll(chatRoomResponse.getData());
        }

        return chatsMessageList;
    }

    /**
     * Maps response from the server after sending new message in chat
     *
     * @param chatRoomSendResponse
     * @return
     */
    public ChatMessage mapChatRoomSend(ChatRoomSendResponse chatRoomSendResponse) {
        ChatMessage chatMessage = new ChatMessage();

        if (chatRoomSendResponse != null) {
            chatMessage.setChat_id(chatRoomSendResponse.getData().getChat_id());
            chatMessage.setCreated_at(chatRoomSendResponse.getData().getCreated_at());
            chatMessage.setId(chatRoomSendResponse.getData().getId());
            chatMessage.setMessage(chatRoomSendResponse.getData().getMessage());
            chatMessage.setUser(chatRoomSendResponse.getData().getUser());
            chatMessage.setUser_id(chatRoomSendResponse.getData().getUser_id());
        }
        return chatMessage;
    }

    /**
     * Maps response from server after creating or editing chat room
     *
     * @param chatCreateResponse
     * @return
     */
    public Chats mapCreateChat(ChatCreateResponse chatCreateResponse) {
        Chats chat = new Chats();

        if (chatCreateResponse != null) {
            chat.setId(chatCreateResponse.getData().getId());
            chat.setLast_chat_message(chatCreateResponse.getData().getLast_chat_message());
            chat.setName(chatCreateResponse.getData().getName());
            chat.setUsers(chatCreateResponse.getData().getUsers());
        }
        return chat;
    }
}
