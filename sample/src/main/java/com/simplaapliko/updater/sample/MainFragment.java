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

package com.simplaapliko.updater.sample;

import android.content.Context;
import android.content.DialogInterface;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.simplaapliko.updater.ChangeLogDialog;
import com.simplaapliko.updater.Updater;
import com.simplaapliko.updater.sample.versions.UpdateFactory;

public class MainFragment extends Fragment implements DialogInterface.OnDismissListener {

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Button updateButton = (Button) rootView.findViewById(R.id.update);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Updater updater = new Updater(getContext());
                if (updater.isVersionCodeChanged()) {
                    updater.onVersionChanged(
                            UpdateFactory.getUpdateListener(getContext(), updater.getNewVersionCode()));

                    showChangeLog();
                }
            }
        });

        Button resetButton = (Button) rootView.findViewById(R.id.reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getContext().getSharedPreferences("com.simplaapliko.updater.preferences", Context.MODE_PRIVATE)
                        .edit()
                        .putInt("version_code", 0)
                        .apply();

            }
        });

        Button showChangeLog = (Button) rootView.findViewById(R.id.showChangeLog);
        showChangeLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLog();
            }
        });

        return rootView;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        Toast.makeText(getContext(), "Dialog dismissed", Toast.LENGTH_SHORT).show();
    }

    private void showChangeLog() {
        DialogFragment dialog = new ChangeLogDialog.Builder()
                .setChangeLog(getString(R.string.change_log_description))
                .setHasPositiveButton(true)
                .build();

        ((ChangeLogDialog) dialog).setOnDismissListener(MainFragment.this);

        dialog.show(getFragmentManager(), ChangeLogDialog.class.getSimpleName());
    }
}
