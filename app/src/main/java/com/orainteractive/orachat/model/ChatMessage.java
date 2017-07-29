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

/**
 * ChatMessage object model
 * Created by kamilabrito on 7/25/17.
 */

public class ChatMessage {

    private int id;
    private int chat_id;
    private int user_id;
    private String message;
    private String created_at;
    private User user;

    /**
     * Chat model constructor
     * @param id
     * @param chat_id
     * @param user_id
     * @param message
     * @param created_at
     * @param user
     */
    public ChatMessage(int id, int chat_id, int user_id, String message, String created_at, User user) {
        this.id = id;
        this.chat_id = chat_id;
        this.user_id = user_id;
        this.message = message;
        this.created_at = created_at;
        this.user = user;
    }

    public ChatMessage() {

    }

    /**
     * Get chat message id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Set chat message id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get chat id
     * @return
     */
    public int getChat_id() {
        return chat_id;
    }

    /**
     * Set chat id
     * @param chat_id
     */
    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    /**
     * Get user chat id
     * @return
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * Set user chat id
     * @param user_id
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * Get message
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set chat message
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Get chat creation date
     * @return
     */
    public String getCreated_at() {
        return created_at;
    }

    /**
     * Set chat creation date
     * @param created_at
     */
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    /**
     * Get chat user information
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     * Set chat user information
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
