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
 * Models the response from the server
 * after creating a new chat
 * Created by kamilabrito on 7/28/17.
 */

public class ChatCreateResponse {

    private Chats data;
    private Pagination meta;

    /**
     * Gets chat received as response after creating a new chat
     *
     * @return
     */
    public Chats getData() {
        return data;
    }

    /**
     * Sets chat received as response after creating a new chat
     *
     * @param data
     */
    public void setData(Chats data) {
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
