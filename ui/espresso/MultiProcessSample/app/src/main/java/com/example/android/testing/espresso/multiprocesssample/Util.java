/*
 * Copyright (C) 2017 The Android Open Source Project
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

package com.example.android.testing.espresso.multiprocesssample;

import static android.os.Process.myPid;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

/**
 * Class contains static util methods for reuse
 */
public class Util {

    private static final String TAG = "Util";

    /**
     * Utility method to update given TextView with the current process string.
     */
    public static void setCurrentRunningProcess(TextView textView, Context activityContext) {
        String currentProcName;
        ActivityManager manager =
                (ActivityManager) activityContext.getSystemService(Context.ACTIVITY_SERVICE);
        for (RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == myPid()) {
                currentProcName = processInfo.processName;
                Log.i(TAG, currentProcName);
                textView.setText(currentProcName);
                break;
            }
        }
    }

}