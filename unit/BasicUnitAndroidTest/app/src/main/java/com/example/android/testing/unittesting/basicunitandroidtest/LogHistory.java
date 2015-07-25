/*
 * Copyright 2015 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.testing.unittesting.basicunitandroidtest;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Model that stores a parcelable log of entries, each with a timestamp.
 */
public class LogHistory implements Parcelable {

    // Used to store the data to be used by the activity.
    private final List<Pair<String, Long>> mData = new ArrayList<>();

    // Creates an empty log.
    public LogHistory() { }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        // Prepare an array of strings and an array of timestamps.
        String[] texts = new String[mData.size()];
        long[] timestamps = new long[mData.size()];

        // Store the data in the arrays.
        for (int i = 0; i < mData.size(); i++) {
            texts[i] = mData.get(i).first;
            timestamps[i] = mData.get(i).second;
        }
        // Write the size of the arrays first.
        out.writeInt(texts.length);

        // Write the two arrays in a specific order.
        out.writeStringArray(texts);
        out.writeLongArray(timestamps);
    }

    public static final Parcelable.Creator<LogHistory> CREATOR
            = new Parcelable.Creator<LogHistory>() {

        @Override
        public LogHistory createFromParcel(Parcel in) {
            return new LogHistory(in);
        }

        @Override
        public LogHistory[] newArray(int size) {
            return new LogHistory[size];
        }
    };

    /**
     * Returns a copy of the current data used by the activity.
     */
    public List<Pair<String, Long>> getData() {
        return new ArrayList<>(mData);
    }

    /**
     * Adds a new entry to the log.
     * @param text the text to be stored in the log
     * @param timestamp the current time in milliseconds since January 1, 1970 00:00:00.0 UTC.
     */
    public void addEntry(String text, long timestamp) {
        mData.add(new Pair<String, Long>(text, timestamp));
    }

    // Constructor used from the CREATOR field that unpacks a Parcel.
    private LogHistory(Parcel in) {
        // First, read the size of the arrays that contain the data.
        int length = in.readInt();

        // Create the arrays to store the data.
        String[] texts = new String[length];
        long[] timestamps = new long[length];

        // Read the arrays in a specific order.
        in.readStringArray(texts);
        in.readLongArray(timestamps);

        // The lengths of both arrays should match or the data is corrupted.
        if (texts.length != timestamps.length) {
            throw new IllegalStateException("Error reading from saved state.");
        }

        // Reset the data container and update the data.
        mData.clear();
        for (int i = 0; i < texts.length; i++) {
            Pair<String, Long> pair = new Pair<>(texts[i], timestamps[i]);
            mData.add(pair);
        }
    }
}