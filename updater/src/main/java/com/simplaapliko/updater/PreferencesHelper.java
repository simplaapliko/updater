/*
 * Copyright (C) 2016 Oleg Kan, @Simplaapliko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.simplaapliko.updater;

import android.content.Context;

public class PreferencesHelper {

    private static final String PREFERENCES = "com.simplaapliko.updater.preferences";

    /**
     * A reference to android:versionCode
     * for example 1
     */
    private static final String PREF_VERSION_CODE = "version_code";

    /**
     * A reference to android:versionName
     * for example 1.3 or 1.3.2
     */
    private static final String PREF_VERSION_NAME = "version_name";

    private Context mContext;

    PreferencesHelper(Context context) {
        mContext = context;
    }

    /**
     * @return -1 if version code was not found.
     */
    int getVersionCode() {
        return mContext.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
                .getInt(PREF_VERSION_CODE, -1);
    }

    void setVersionCode(int code) {
        mContext.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
                .edit()
                .putInt(PREF_VERSION_CODE, code)
                .apply();
    }

    /**
     * @return null if version name was not found.
     */
    String getVersionName() {
        return mContext.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
                .getString(PREF_VERSION_NAME, null);
    }

    void setVersionName(String name) {
        mContext.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
                .edit()
                .putString(PREF_VERSION_NAME, name)
                .apply();
    }
}
