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

import com.orainteractive.orachat.model.User;
import com.orainteractive.orachat.model.UserResponse;

import javax.inject.Inject;

/**
 * Created by kamilabrito on 7/26/17.
 */

public class UserMapper {

    @Inject
    public UserMapper() {
    }

    public User mapUser(UserResponse response) {
        UserResponse userResponse = new UserResponse();
        User myUser = new User();

        if (response != null) {
            userResponse.setData(response.getData());
            userResponse.setMeta(response.getMeta());

            myUser.setId(userResponse.getData().getId());
            myUser.setName(userResponse.getData().getName());
            myUser.setEmail(userResponse.getData().getEmail());
        }

        return myUser;
    }
}
