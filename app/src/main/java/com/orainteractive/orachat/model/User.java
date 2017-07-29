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

import java.io.Serializable;

/**
 * User object model
 * Created by kamilabrito on 7/25/17.
 */

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int id;
    private String name;
    private String email;
    private String password;
    private String password_confirmation;
    private String authorization;
    private String contentType;

    /**
     * Get user autorization code
     * @return
     */

    public String getAuthorization() {
        return authorization;
    }

    /**
     * Set user autorization code
     * @param authorization
     */
    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    /**
     * User object constructor
     * @param id
     * @param name
     * @param email
     * @param password
     * @param password_confirmation
     */
    public User(int id, String name, String email, String password, String password_confirmation) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.password_confirmation = password_confirmation;
    }

    /**
     * User object constructor
     */
    public User() {
    }

    /**
     * Get user id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Set user id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get user name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set user name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get user email
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set user email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get user password
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set user password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get user confirmation password
     * @return
     */
    public String getPasswordConfirmation() {
        return password_confirmation;
    }

    /**
     * Set user confirmation password
     * @param password_confirmation
     */
    public void setPasswordConfirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public String getContentType() {
        return contentType;
    }
}
