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

package com.orainteractive.orachat.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.orainteractive.orachat.R;
import com.orainteractive.orachat.base.BaseActivity;
import com.orainteractive.orachat.view.fragment.AccountFragment;
import com.orainteractive.orachat.view.fragment.ChatsFragment;

import butterknife.BindView;

/**
 *
 * Created by kamilabrito on 7/27/17.
 */

public class HomeActivity extends BaseActivity implements HomeView {

    @BindView(R.id.bn_home)
    BottomNavigationView mBnHome;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        mBnHome.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                handleBottomNavigationItemSelected(item);
                return true;
            }
        });

        changeFragment(new ChatsFragment());

    }

    private void changeFragment(Fragment fragment) {
        FragmentTransaction cf = getSupportFragmentManager().beginTransaction();
        cf.replace(R.id.fragmentContainer, fragment);
        cf.commit();
    }


    private void handleBottomNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_chats:
                changeFragment(new AccountFragment());
                break;
            case R.id.action_account:
                changeFragment(new ChatsFragment());
                break;
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }
}
