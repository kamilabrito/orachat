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

package com.orainteractive.orachat.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.orainteractive.orachat.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by kamilabrito on 7/29/17.
 */

public class Utils {

    /**
     * Verifies if the device is connected to the internet
     *
     * @param context
     * @return
     */
    public static boolean isNetAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()){
                return true;
            }
        }
        return false;
    }

    /**
     * Formats date to show on chat header
     *
     * @param createdAt
     * @return
     * @throws ParseException
     */
    public static String formatDateChatHeader(String createdAt) throws ParseException {

        SimpleDateFormat rawDateFormater = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
        try {
            Date rawFormatedDate = rawDateFormater.parse(createdAt);
            rawDateFormater.setTimeZone(TimeZone.getDefault());
            rawDateFormater = new SimpleDateFormat("MMM dd yyyy");
            String formatedDate = rawDateFormater.format(rawFormatedDate);
            return formatedDate;
        } catch (ParseException e) {
            throw new ParseException("The current date format is invalid", e.getErrorOffset());
        }
    }


    /**
     * Formats date and returns difference
     *
     * @param eventDate
     * @param context
     * @return
     * @throws ParseException
     */
    public static String getDateAgo(String eventDate, Context context) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
        try {
            Date date = sdf.parse(eventDate);
            Date now = new Date(System.currentTimeMillis());
            long days = getDateDiff(date, now, TimeUnit.DAYS);
            if (days < 7)
                return days + context.getResources().getString(R.string.days_ago);
            else
                return days / 7 + context.getResources().getString(R.string.weeks_ago);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        throw new ParseException("The current date format is invalid", 0);
    }

    private static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }


}
