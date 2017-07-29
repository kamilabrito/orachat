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
 * Models the object of chat room
 * that is send to the server
 * Created by kamilabrito on 7/28/17.
 */

public class ChatRoomSend {

    private String message;

    /**
     * Constructor of chatroom object
     *
     * @param message
     */
    public ChatRoomSend(String message) {
        this.message = message;
    }

    /**
     * Sets the message to chatroom object
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets message from chatroom object
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
