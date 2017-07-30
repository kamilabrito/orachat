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

package com.orainteractive.orachat.view.fragment.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.orainteractive.orachat.R;
import com.orainteractive.orachat.base.BaseFragment;
import com.orainteractive.orachat.dagger.components.DaggerChatComponent;
import com.orainteractive.orachat.dagger.module.ChatModule;
import com.orainteractive.orachat.model.User;
import com.orainteractive.orachat.presenter.AccountPresenter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by kamilabrito on 7/27/17.
 */

public class AccountFragment extends BaseFragment implements AccountView, View.OnClickListener{


    @Inject
    AccountPresenter mPresenter;

    @BindView(R.id.et_account_name)
    EditText etAccountName;
    @BindView(R.id.et_account_email)
    EditText etAccountEmail;
    @BindView(R.id.et_account_password)
    EditText etPassword;
    @BindView(R.id.et_account_password_confirmation)
    EditText etPassworConfirmation;
    @BindView(R.id.btn_account_edit)
    Button btnAccountSave;
    @BindView(R.id.pb_account)
    ProgressBar pbAccount;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnAccountSave.setOnClickListener(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_account;
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerChatComponent.builder()
                .applicationComponent(getApplicationComponent())
                .chatModule(new ChatModule(this))
                .build().inject(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_account_edit:
                pbAccount.setVisibility(View.VISIBLE);
                mPresenter.updateAccountInformation(etAccountName.getText().toString(),
                        etAccountEmail.getText().toString(),
                        etPassword.getText().toString(),
                        etPassworConfirmation.getText().toString());
                break;
        }

    }

    /**
     * Shows toast with empty fields message
     */
    @Override
    public void showEmptyFieldError() {
        pbAccount.setVisibility(View.GONE);
        Toast.makeText(getContext(), getActivity().getResources().
                getString(R.string.empty_field), Toast.LENGTH_LONG).show();
    }

    /**
     * Shows toast with error message
     */
    @Override
    public void showError() {
        pbAccount.setVisibility(View.GONE);
        Toast.makeText(getContext(), getActivity().getResources().
                getString(R.string.request_error), Toast.LENGTH_LONG).show();
    }

    /**
     * Show toast to confirm information was updated
     */
    @Override
    public void confirmUpdate() {
        pbAccount.setVisibility(View.GONE);
        Toast.makeText(getActivity(), R.string.user_information_updated, Toast.LENGTH_SHORT).show();
    }
}
