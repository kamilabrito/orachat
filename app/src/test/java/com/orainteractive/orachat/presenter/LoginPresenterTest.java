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

package com.orainteractive.orachat.presenter;

import com.orainteractive.orachat.R;
import com.orainteractive.orachat.view.login.LoginView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by kamilabrito on 7/29/17.
 */

@RunWith(PowerMockRunner.class)
public class LoginPresenterTest {

    @InjectMocks
    private LoginPresenter presenter;
    @Mock private LoginView mView;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void loginWithExistingUserOneEmptyFields() throws Exception {
        presenter.loginWithExistingUser("alex@orainteractive.com", "");
        verify(mView, atLeastOnce()).showError(R.string.empty_field);
    }

    @Test
    public void loginWithExistingUserEmptyFields() throws Exception {
        presenter.loginWithExistingUser("", "");
        verify(mView, atLeastOnce()).showError(R.string.empty_field);
    }

    @Test
    public void registerNewUserOneEmptyFields() throws Exception {
        presenter.registerNewUser("", "alex@gmail.com", "secret", "secret");
        verify(mView, atLeastOnce()).showError(R.string.empty_field);
    }

    @Test
    public void registerNewUserAllEmptyFields() throws Exception {
        presenter.registerNewUser("", "", "", "");
        verify(mView, atLeastOnce()).showError(R.string.empty_field);
    }

}
