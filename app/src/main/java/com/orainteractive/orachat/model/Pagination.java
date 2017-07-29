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
 * Pagination object model
 * Created by kamilabrito on 7/25/17.
 */

public class Pagination {

    private int current_page;
    private int per_page;
    private int page_count;
    private int total_count;

    /**
     * Pagination object contructor
     *
     * @param current_page
     * @param per_page
     * @param page_count
     * @param total_count
     */
    public Pagination(int current_page, int per_page, int page_count, int total_count) {
        this.current_page = current_page;
        this.per_page = per_page;
        this.page_count = page_count;
        this.total_count = total_count;
    }

    /**
     * Pagination object contructor
     */
    public Pagination() {
    }

    /**
     * Get current page
     *
     * @return
     */
    public int getCurrent_page() {
        return current_page;
    }

    /**
     * Set current page
     *
     * @param current_page
     */
    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    /**
     * Get per page
     *
     * @return
     */
    public int getPer_page() {
        return per_page;
    }

    /**
     * Set per page
     *
     * @param per_page
     */
    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    /**
     * Get page count
     *
     * @return
     */
    public int getPage_count() {
        return page_count;
    }

    /**
     * Set page count
     *
     * @param page_count
     */
    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    /**
     * Get total count
     *
     * @return
     */
    public int getTotal_count() {
        return total_count;
    }

    /**
     * Set page count
     *
     * @param total_count
     */
    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
}
