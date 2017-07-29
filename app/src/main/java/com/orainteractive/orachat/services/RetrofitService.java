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

package com.orainteractive.orachat.services;

import com.orainteractive.orachat.model.ChatMessage;
import com.orainteractive.orachat.model.ChatRoomSend;
import com.orainteractive.orachat.model.ChatRoomSendResponse;
import com.orainteractive.orachat.model.ChatsResponse;
import com.orainteractive.orachat.model.Login;
import com.orainteractive.orachat.model.User;
import com.orainteractive.orachat.model.UserResponse;
import com.orainteractive.orachat.model.ChatRoomResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by kamilabrito on 7/22/17.
 */

public interface RetrofitService {

    @POST("/auth/login")
    Observable<UserResponse> loginWithExistingUser(@Body Login login);

    @POST("/auth/login")
    Call<UserResponse> loginWithUser(@Body Login login);

    @POST("/users")
    Call<UserResponse> createNewUser(@Body User newUser);

    @GET("/chats")
    Observable<ChatsResponse> getChatsList(@Header("Authorization") String authorization,
                                           @Header("Content-Type") String contentType,
                                           @Query("page") int page,
                                           @Query("limit") int limit);

    @GET("/chats/{id}/chat_messages")
    Observable<ChatRoomResponse> getChatRoomMessages(@Header("Authorization") String authorization,
                                                     @Header("Content-Type") String contentType,
                                                     @Query("id") int id,
                                                     @Query("page") int page,
                                                     @Query("limit") int limit);

    @POST("/chats/{id}/chat_messages")
    Observable<ChatRoomSendResponse> sendChatRoomMessages(@Header("Authorization") String authorization,
                                                          @Header("Content-Type") String contentType,
                                                          @Query("id") int id,
                                                          @Body ChatRoomSend message);



}
