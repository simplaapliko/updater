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
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.List;

public class Updater {

    // Inner classes

    public interface OnVersionChangeListener {
        void onVersionChange();
    }


    private static final String TAG = "Updater";

    private Context mContext;
    private int mNewVersionCode;
    private int mOldVersionCode;
    private String mNewVersionName;
    private String mOldVersionName;


    // Constructors

    public Updater(Context context) {
        mContext = context.getApplicationContext();

        try {
            PackageInfo packageInfo = mContext.getPackageManager()
                    .getPackageInfo(mContext.getPackageName(), PackageManager.GET_META_DATA);

            mNewVersionCode = packageInfo.versionCode;
            mNewVersionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "unable to get version code.", e);
            mNewVersionCode = -1;
            mNewVersionName = null;
        }

        mOldVersionCode = new PreferencesHelper(mContext)
                .getVersionCode();
        mOldVersionName = new PreferencesHelper(mContext)
                .getVersionName();
    }


    // Public API

    /**
     * @see PreferencesHelper#getVersionCode()
     */
    public int getNewVersionCode() {
        return mNewVersionCode;
    }

    /**
     * @see PreferencesHelper#getVersionCode()
     */
    public int getOldVersionCode() {
        return mOldVersionCode;
    }

    /**
     * @see PreferencesHelper#getVersionName()
     */
    public String getNewVersionName() {
        return mNewVersionName;
    }

    /**
     * @see PreferencesHelper#getVersionName()
     */
    public String getOldVersionName() {
        return mOldVersionName;
    }

    /**
     * @see Versions#getMajorVersion(String)
     */
    public String getNewMajorVersionName() {
        return Versions.getMajorVersion(mNewVersionName);
    }

    /**
     * @see Versions#getMajorVersion(String)
     */
    public String getOldMajorVersionName() {
        return Versions.getMajorVersion(mOldVersionName);
    }

    /**
     * @see Versions#getMinorVersion(String)
     */
    public String getNewMinorVersionName() {
        return Versions.getMinorVersion(mNewVersionName);
    }

    /**
     * @see Versions#getMinorVersion(String)
     */
    public String getOldMinorVersionName() {
        return Versions.getMinorVersion(mOldVersionName);
    }

    /**
     * @see Versions#getPatchVersion(String)
     */
    public String getNewPatchVersionName() {
        return Versions.getPatchVersion(mNewVersionName);
    }

    /**
     * @see Versions#getPatchVersion(String)
     */
    public String getOldPatchVersionName() {
        return Versions.getPatchVersion(mOldVersionName);
    }

    public boolean isFirstLaunch() {
        return mOldVersionCode == -1;
    }

    /**
     * @return true if app version code updated.
     * <p><b><i>Note: Returns false on the first launch</i></b>
     */
    public boolean isVersionCodeChanged() {
        return mOldVersionCode != -1 &&
                mNewVersionCode != mOldVersionCode;
    }

    /**
     * @return true if app version name updated.
     * <p><b><i>Note: Returns false on the first launch</i></b>
     */
    public boolean isVersionNameChanged() {
        if (mNewVersionName == null || mOldVersionName == null) {
            return false;
        }

        return Versions.compareVersion(mNewVersionName, mOldVersionName) != 0;
    }

    /**
     * @return true if major version changed
     * <p><b><i>Note: Returns false on the first launch</i></b>
     */
    public boolean isMajorVersionChanged() {
        if (mNewVersionName == null || mOldVersionName == null) {
            return false;
        }

        String newVersion = Versions.getMajorVersion(mNewVersionName);
        String oldVersion = Versions.getMajorVersion(mOldVersionName);

        return Versions.compareVersion(newVersion, oldVersion) == 1;
    }

    /**
     * @return true if minor version changed
     * <p><b><i>Note: Returns false on the first launch</i></b>
     */
    public boolean isMinorVersionChanged() {
        if (mNewVersionName == null || mOldVersionName == null) {
            return false;
        }

        if (isMajorVersionChanged()) {
            return true;
        }

        String newVersion = Versions.getMinorVersion(mNewVersionName);
        String oldVersion = Versions.getMinorVersion(mOldVersionName);

        return Versions.compareVersion(newVersion, oldVersion) == 1;
    }

    /**
     * @return true if patch version changed
     * <p><b><i>Note: Returns false on the first launch</i></b>
     */
    public boolean isPatchVersionChanged() {
        if (mNewVersionName == null || mOldVersionName == null) {
            return false;
        }

        if (isMinorVersionChanged()) {
            return true;
        }

        String newVersion = Versions.getPatchVersion(mNewVersionName);
        String oldVersion = Versions.getPatchVersion(mOldVersionName);

        return Versions.compareVersion(newVersion, oldVersion) == 1;
    }

    public void onVersionChanged(List<OnVersionChangeListener> listeners) {

        for (OnVersionChangeListener listener : listeners) {
            listener.onVersionChange();
        }

        PreferencesHelper preferencesHelper = new PreferencesHelper(mContext);
        preferencesHelper.setVersionCode(mNewVersionCode);
        preferencesHelper.setVersionName(mNewVersionName);
    }

}
