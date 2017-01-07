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

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.webkit.WebView;

public class ChangeLogDialog extends DialogFragment {

    public static class Builder {

        private String mTitle;
        private String mChangeLog;

        private boolean mHasPositiveButton;

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setChangeLog(String changeLog) {
            mChangeLog = changeLog;
            return this;
        }

        public Builder setHasPositiveButton(boolean hasPositiveButton) {
            mHasPositiveButton = hasPositiveButton;
            return this;
        }

        public ChangeLogDialog build() {

            return newInstance(mTitle, mChangeLog, mHasPositiveButton);
        }
    }

    private static final String TITLE_KEY = "TITLE_KEY";
    private static final String CHANGE_LOG_KEY = "CHANGE_LOG_KEY";
    private static final String HAS_POSITIVE_BUTTON_KEY = "HAS_POSITIVE_BUTTON_KEY";

    private DialogInterface.OnDismissListener mOnDismissListener;

    private String mTitle;
    private String mChangeLog;
    private boolean mHasPositiveButton;

    private static ChangeLogDialog newInstance(String title,
                                               String changeLog,
                                               boolean hasPositiveButton) {

        ChangeLogDialog fragment = new ChangeLogDialog();
        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);
        args.putString(CHANGE_LOG_KEY, changeLog);
        args.putBoolean(HAS_POSITIVE_BUTTON_KEY, hasPositiveButton);

        fragment.setArguments(args);
        return fragment;
    }

    public ChangeLogDialog() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mTitle = args.getString(TITLE_KEY);
            if (mTitle == null) {
                // set default title
                mTitle = getString(R.string.u_change_log_title);
            }

            mChangeLog = args.getString(CHANGE_LOG_KEY);
            mHasPositiveButton = args.getBoolean(HAS_POSITIVE_BUTTON_KEY);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // get root view
        View rootView = getActivity().getLayoutInflater().inflate(R.layout.u_dialog_fragment_change_log, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(rootView);

        builder.setTitle(mTitle);

        // set buttons
        if (mHasPositiveButton) {
            builder.setPositiveButton(
                    android.R.string.ok,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
        }

        ((WebView) rootView.findViewById(R.id.description))
                .loadDataWithBaseURL(null, mChangeLog, "text/html", "utf-8", null);

        return builder.create();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mOnDismissListener = null;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        if (mOnDismissListener != null) {
            mOnDismissListener.onDismiss(dialog);
        }
    }

    public DialogInterface.OnDismissListener getOnDismissListener() {
        return mOnDismissListener;
    }

    public void setOnDismissListener(final DialogInterface.OnDismissListener onDismissListener) {
        mOnDismissListener = onDismissListener;
    }
}