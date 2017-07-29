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
 * Models the server response after
 * receiving list of chatrooms
 * Created by kamilabrito on 7/27/17.
 */

public class ChatRoomResponse {

    private List<ChatMessage> data;
    private Pagination meta;

    /**
     * Get chatrooms list
     *
     * @return
     */
    public List<ChatMessage> getData() {
        return data;
    }

    /**
     * Sets chatroom list
     *
     * @param data
     */
    public void setData(List<ChatMessage> data) {
        this.data = data;
    }

    /**
     * Get pagination information
     *
     * @return
     */
    public Pagination getMeta() {
        return meta;
    }

    /**
     * Sets pagination information
     *
     * @param meta
     */
    public void setMeta(Pagination meta) {
        this.meta = meta;
    }
}
