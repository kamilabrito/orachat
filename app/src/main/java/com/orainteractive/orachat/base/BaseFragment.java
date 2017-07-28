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

package com.orainteractive.orachat.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orainteractive.orachat.app.ChatApplication;
import com.orainteractive.orachat.dagger.components.ApplicationComponent;

import butterknife.ButterKnife;

/**
 * Base Fragment class with implementing
 * common methods to most activities.
 * Created by kamilabrito on 7/27/17.
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        ButterKnife.bind(this, view);

        resolveDaggerDependency();
        return view;
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((ChatApplication) getActivity().getApplication()).getApplicationComponent();
    }

    protected void resolveDaggerDependency() {}
    protected abstract int getContentView();
}
