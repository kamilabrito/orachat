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

import android.content.Context;
import android.content.SharedPreferences;

import com.orainteractive.orachat.R;

import javax.inject.Inject;

/**
 * Created by kamilabrito on 7/26/17.
 */

public class SharedPrefences {

    private static final String USER_PREF_FILE = "user_pref_file";

    @Inject
    public SharedPrefences() {
    }

    public void saveUserOnStorage(Context context, User userInformation) {

        SharedPreferences sharedPref = context.getSharedPreferences(USER_PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(context.getResources().getString(R.string.save_user_id), userInformation.getId());
        editor.putString(context.getResources().getString(R.string.save_user_name), userInformation.getName());
        editor.putString(context.getResources().getString(R.string.save_user_email), userInformation.getEmail());
        editor.putString(context.getResources().getString(R.string.save_user_authorization), userInformation.getAuthorization());
        editor.commit();
    }

    public User readUserFromStorage(Context context) {
        User user = new User();

        SharedPreferences sharedPref = context.getSharedPreferences(USER_PREF_FILE, Context.MODE_PRIVATE);
        user.setId(sharedPref.getInt(context.getResources().getString(R.string.save_user_id), 0));
        user.setName(sharedPref.getString(context.getResources().getString(R.string.save_user_name), null));
        user.setEmail(sharedPref.getString(context.getResources().getString(R.string.save_user_email), null));
        user.setAuthorization(sharedPref.getString(context.getResources().getString(R.string.save_user_authorization), null));

        return user;
    }




}
