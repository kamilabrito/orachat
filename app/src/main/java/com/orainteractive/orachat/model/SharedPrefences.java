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

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.orainteractive.orachat.R;

import javax.inject.Inject;

/**
 * Created by kamilabrito on 7/26/17.
 */

public class SharedPrefences {

    @Inject
    public SharedPrefences() {
    }

    public void saveUserOnStorage(Activity activity, User userInformation) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(activity.getResources().getString(R.string.save_user_id), userInformation.getId());
        editor.putString(activity.getResources().getString(R.string.save_user_name), userInformation.getName());
        editor.putString(activity.getResources().getString(R.string.save_user_email), userInformation.getEmail());
        editor.putString(activity.getResources().getString(R.string.save_user_authorization), userInformation.getAuthorization());
        editor.commit();
    }

    public User readUserFromStorage(Activity activity) {
        User user = new User();

        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        user.setId(sharedPref.getInt(activity.getResources().getString(R.string.save_user_id), 0));
        user.setName(sharedPref.getString(activity.getResources().getString(R.string.save_user_name), null));
        user.setEmail(sharedPref.getString(activity.getResources().getString(R.string.save_user_email), null));
        user.setAuthorization(sharedPref.getString(activity.getResources().getString(R.string.save_user_authorization), null));

        return user;
    }




}
