package com.example.android.testing.robolectric.BasicSample;

import android.content.Intent;
import android.widget.TextView;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

/**
 * Robolectric tests for {@link ShowTextActivity}.
 */
@RunWith(RobolectricTestRunner.class)
public class ShowTextActivityTest {

    private static final String MESSAGE = "Example";

    private ShowTextActivity activity;

    @Before
    public void setUp() {
        Intent intent = new Intent(RuntimeEnvironment.application, ShowTextActivity.class);
        intent.putExtra(ShowTextActivity.KEY_EXTRA_MESSAGE, MESSAGE);
        activity = Robolectric.buildActivity(ShowTextActivity.class, intent).setup().get();
    }

    @Test
    public void displayIntentMessage() {
        TextView show_text_view = activity.findViewById(R.id.show_text_view);
        Assert.assertThat(show_text_view.getText().toString(), Matchers.equalTo(MESSAGE));
    }
}
