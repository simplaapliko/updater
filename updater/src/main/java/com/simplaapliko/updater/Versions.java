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

package com.simplaapliko.updater;

class Versions {

    /**
     * @return 1 if version1 > version2 , -1 if version1 < version2, otherwise returns 0.
     * @throws IllegalArgumentException if version1 or version2 is empty.
     */
    static int compareVersion(String version1, String version2) {

        if (version1 == null && version2 == null) {
            return 0;
        } else if (version1 != null && version2 == null) {
            return 1;
        } else if (version1 == null) {
            return -1;
        }
        
        if (version1.isEmpty() || version2.isEmpty()) {
            throw new IllegalArgumentException();
        }
        
        String[] sequences1 = version1.split("\\.");
        String[] sequences2 = version2.split("\\.");

        int length = Math.max(sequences1.length, sequences2.length);

        for (int i = 0; i < length; i++) {

            Integer sequence1 = i < sequences1.length ? Integer.valueOf(sequences1[i]) : 0;
            Integer sequence2 = i < sequences2.length ? Integer.valueOf(sequences2[i]) : 0;

            int compare = sequence1.compareTo(sequence2);
            if (compare != 0) {
                return compare;
            }
        }

        return 0;
    }

    /**
     * @return major version, if not exist returns null.
     * <p>1.3.2, major version = 1
     */
    static String getMajorVersion(String version) {
        if (version == null || version.isEmpty()) {
            return null;
        }

        String[] sequences = version.split("\\.");
        return sequences[0];
    }

    /**
     * @return minor version, if not exist returns null.
     * <p>1.3.2, minor version = 1.3
     */
    static String getMinorVersion(String version) {
        if (version == null || version.isEmpty()) {
            return null;
        }

        String[] sequences = version.split("\\.");

        if (sequences.length < 2) {
            return null;
        }

        return sequences[0] + "." + sequences[1];
    }

    /**
     * @return patch version, if not exist returns null.
     * <p>1.3.2, patch version = 1.3.2
     */
    static String getPatchVersion(String version) {
        if (version == null || version.isEmpty()) {
            return null;
        }

        String[] sequences = version.split("\\.");

        if (sequences.length < 3) {
            return null;
        }

        return version;
    }
}
