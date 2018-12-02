/*
 * Copyright (C) 2016 Oleg Kan
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

package com.simplaapliko.updater.sample.versions;

import android.content.Context;

import com.simplaapliko.updater.Updater;

import java.util.ArrayList;
import java.util.List;

public class UpdateFactory {

    public static List<Updater.OnVersionChangeListener> getUpdateListener(Context context,
            int version) {

        List<Updater.OnVersionChangeListener> listeners = new ArrayList<>();

        switch (version) {
            case 2:
                listeners.add(new UpdateToVersion2(context));
                break;
        }

        return listeners;
    }
}
