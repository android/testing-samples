package com.example.android.testing.espresso.CustomMatcherSample;

import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Choreographer;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static android.os.Looper.getMainLooper;

@RunWith(AndroidJUnit4.class)
public class FrameTimingTest {

    @Test
    public void measureVSync() throws InterruptedException {
        LoggingFrameCallback loggingFrameCallback = new LoggingFrameCallback();
        new Handler(getMainLooper()).post(
                () -> Choreographer.getInstance().postFrameCallback(loggingFrameCallback));

        loggingFrameCallback.await();
    }

    private static class LoggingFrameCallback implements Choreographer.FrameCallback {

        private CountDownLatch latch = new CountDownLatch(50);
        private long lastTime = SystemClock.uptimeMillis();
        private long randomDelay = 0;

        @Override
        public void doFrame(long frameTimeNanos) {
            Log.d("Timing", String.format("frame delta %d ms last delay %d ms", SystemClock.uptimeMillis() - lastTime, randomDelay));
            lastTime = SystemClock.uptimeMillis();
            latch.countDown();
            randomDelay = Math.round(Math.random() * 30);
            Choreographer.getInstance().postFrameCallbackDelayed(this, randomDelay);
        }

        public void await() throws InterruptedException {
            latch.await();
        }
    }
}
