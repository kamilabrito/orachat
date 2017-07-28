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

package com.orainteractive.orachat.model;

import java.util.List;

/**
 * Chats object model
 * Created by kamilabrito on 7/25/17.
 */

public class Chats {

    public int id;
    public String name;
    public List<User> users;
    public ChatMessage last_chat_message;

    /**
     * Chats constructor
     * @param id
     * @param name
     * @param users
     * @param last_chat_message
     */
    public Chats(int id, String name, List<User> users, ChatMessage last_chat_message) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.last_chat_message = last_chat_message;
    }

    public Chats() {
    }

    /**
     * Get chats id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Set chats id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get chats name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set chats name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get users in chat
     * @return
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Set users in chat
     * @param users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * Get last chat message
     * @return
     */
    public ChatMessage getLast_chat_message() {
        return last_chat_message;
    }

    /**
     * Set last chat message
     * @param last_chat_message
     */
    public void setLast_chat_message(ChatMessage last_chat_message) {
        this.last_chat_message = last_chat_message;
    }
}
